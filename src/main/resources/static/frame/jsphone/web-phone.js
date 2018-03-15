(function ($) {

    let socket = null;

    $.Phone = function (target,options,callback) {

        this.sessionId = null;//一次连接登录的会话ID
        this.agentIndex = null;
        this.notReadyReason = null;//示忙原因
        this.platformType = null;//平台类型
        this.platServiceUrl = null;//软电话中间件服务基地址
        this.pollingTimer = null;//轮循事件的定时器
        this.statusTimer = null;//软电话状态定时器
        this.durationTime = 0;//软电话状态持续时长
        this.historyDialNums = new Array(10);//保留最新的10个外呼号码记录

        if(target){
            console.log(target);
            $(target).html(tmp);
        }

        if(options.agent){
            let agent = options.agent;
            this.agentId = agent.agentId;//工号
            this.password = agent.password;//密码
            this.extension = agent.station;//分机
        }

        if(options.socketIp){
            //创建Socket.IO实例，建立连接
            let url = "http://" + options.socketIp + "?userId=" + options.agent.agentId;
            socket = io.connect(url);
        }

        if(socket){

            if(options.isLogon){
                debugger
                let agent = options.agent;
                socket.emit("phone:login",{
                        agentId: agent.agentId,
                        password: agent.password,
                        station: agent.station
                    });
            }

            // 添加一个连接监听器
            socket.on('connect',function() {
                console.log('已连接server');
                $phone.showStatusDuration();
            });

            // 添加一个关闭连接的监听器
            socket.on('disconnect',function() {
                console.log('client断开');
            });

            socket.on('event_error',function(data) {
                console.log('event_error=' + data);
                callback('event_error',data);
            });

            socket.on('event_login',function(data) {
                console.log('登录成功',data);
                callback('event_login',data);
                $phone.durationTime = 0;
            });

            socket.on('event_logout',function(data) {
                console.log('签出成功',data);
                window.clearInterval(this.pollingTimer);
                callback('event_logout',data);
            });

            socket.on('event_loggingin',function(data) {
                console.log('签入中',data);

                $phone.logingStatus();

                callback('event_loggingin',data);
            });

            socket.on('event_newservice',function(data) {
                console.log('服务事件',data);

                //$phone.logingStatus();

                callback('event_newservice',data);
            });


            socket.on('event_idle',function(data) {
                console.log('进入空闲',data);

                $phone.idleStatus();

                callback('event_idle',data);
            });

            socket.on('event_notready',function(data) {
                console.log('进入未就绪',data);

                $phone.notReadyStatus();
                callback('event_notready',data);
            });

            socket.on('event_dialing',function(data) {
                console.log('振铃中',data);

                $phone.alertingStatus(data);
                callback('event_dialing',data);
            });

            socket.on('event_callclear',function(data) {
                console.log('挂断事件',data);

                $phone.hiddenPhoneNum();
                //$phone.alertingStatus(data);
                callback('event_callclear',data);
            });


            socket.on('event_screenpop',function(data) {
                console.log('弹屏事件',data);

                callback('event_screenpop',data);
            });

            socket.on('event_active',function(data) {
                console.log('接通中',data);

                $phone.activeStatus(data);
                callback('event_active',data);
            });

            socket.on('event_hold',function(data) {
                console.log('保持中',data);
                $phone.holdingStatus(data);
                callback('event_hold',data);
            });

            socket.on('event_consult',function(data) {
                console.log('咨询事件',data);
               // $phone.holdingStatus(data);
                callback('event_consult',data);
            });

            socket.on('event_conference',function(data) {
                console.log('会议事件',data);
                //$phone.holdingStatus(data);
                callback('event_conference',data);
            });

            socket.on('event_monitorstate',function(data) {
                console.log('管理事件',data);
                //$phone.holdingStatus(data);
                callback('event_monitorstate',data);
            });

            socket.on('event_logoutpending',function(data) {
                console.log('登出事件',data);
                //$phone.holdingStatus(data);
                callback('event_logoutpending',data);
            });

            socket.on('event_wrap',function(data) {
                console.log('后处理',data);

                $phone.acwStatus(data);
                callback('event_wrap',data);
            });
        }


        $('.logon').on('click', function(e){
            e.preventDefault();
            console.log('phone:login');
            socket.emit("phone:login",{
                agentId: $phone.agentId,
                password: $phone.password,
                station: $phone.extension
            });
        });

        //签出
        $('.logout').on('click', function(e){
            e.preventDefault();
            console.log('phone:logout');
            socket.emit("phone:logout",$phone.agentId);
            $phone.logoutStatus();
        });

        //示忙
        $('.busy').on('click', function(e){
            e.preventDefault();
            console.log('phone:busy');
            socket.emit("phone:busy",{
                agentId: $phone.agentId,
                notreadyreason: 0
            });
        });

        //小休
        $('.rest').on('click', function(e){
            e.preventDefault();
            console.log('phone:rest');
            socket.emit("phone:rest",$phone.agentId);
        });

        //示闲
        $('.idle').on('click', function(e){
            e.preventDefault();
            socket.emit("phone:idle",$phone.agentId);
        });

        //手动外拨
        $('.dial').on('click', function(e){
            e.preventDefault();
            let phoneNumber = '15986757872';
            $phone.showPhoneNum(phoneNumber);
            socket.emit("phone:dialCall",{
                agentId: $phone.agentId,
                phoneNumber: phoneNumber
            });
        });

        //挂断
        $('.hangup').on('click', function(e){
            e.preventDefault();
            socket.emit("phone:hangup",$phone.agentId);
        });

        //保持
        $('.hold').on('click', function(e){
            e.preventDefault();
            socket.emit("phone:hold",$phone.agentId);
        });

        //取消保持
        $('.unHold').on('click', function(e){
            e.preventDefault();
            console.log('phone:unHold');
            socket.emit("phone:unHold",$phone.agentId);
        });

        //咨询
        $('.consult').on('click', function(e){
            e.preventDefault();

            //打开界面，选择咨询 0-转坐席 、1-转外部号码、 2- 技能组
            $phone.consult("test1","0");
        });

        //转接
        $('.transfer').on('click', function(e){
            e.preventDefault();
            socket.emit("phone:transfer",$phone.agentId);
        });

        //会议
        $('.conference').on('click', function(e){
            e.preventDefault();
            socket.emit("phone:conference",$phone.agentId);
        });

        //后处理
        $('.acw').on('click', function(e){
            e.preventDefault();
            socket.emit("phone:acw",$phone.agentId);
        });

        //手动外拨
        this.dial = function (phoneNumber) {
            $phone.showPhoneNum(phoneNumber);
            socket.emit("phone:dialCall",{
                agentId: $phone.agentId,
                phoneNumber: phoneNumber
            });
        };

        //签出
        this.logout = function () {
            socket.emit("phone:logout",$phone.agentId);
            $phone.logoutStatus();
        };

        this.consult = function (number,transferWay) {
            socket.emit("phone:consult",{
                agentId: $phone.agentId,
                number: number,
                transferWay:transferWay
            });
        };

        //签入中
        this.logingStatus = function () {
            $phone.durationTime = 0;
            $phone.showPhoneByEvent("签入中");
        };

        //已签入
        this.logonStatus = function () {

        };

        //已签出
        this.logoutStatus = function () {
            window.clearInterval(this.pollingTimer);
            $(".navbar-phone").width("60%");
            $phone.showPhoneByEvent("未登录","","idle|dial|busy|rest|logout|unHold|monitor|hold|consult|transfer|conference|hangup");
        };

        //示忙中
        this.notReadyStatus = function (data) {
            $phone.durationTime = 0;
            $phone.showPhoneByEvent("示忙中","dial|idle|logout|monitor","busy|acw|rest");
        };

        //示闲中
        this.idleStatus = function () {
            $phone.durationTime = 0;
            $phone.showPhoneByEvent("空闲中","busy|rest|logout","idle|dial|acw|monitor");
        };

        //拔号中
        this.dialingStatus = function (data) {

        };

        //通话中
        this.activeStatus = function (data) {
            $(".navbar-phone").width("80%");
            $phone.showPhoneByEvent("通话中","hold|consult|transfer|conference|hangup","idle|dial|busy|rest|logout|unHold|monitor");
        };

        //后处理
        this.acwStatus = function (data) {
            $phone.showPhoneByEvent("后处理","acw","hold|consult|transfer|conference|hangup");
        };

        //震铃
        this.alertingStatus = function (data) {
            $('.showStatus').html("振铃中");
        };

        //保持中
        this.holdingStatus = function (data) {
            $phone.showPhoneByEvent("保持中","unHold","hold");
        };

        //显示电话号码
        this.showPhoneNum = function (phone) {
            $('.content').addClass("max");
            $(".navbar-phone").width("75%");
            $('.phoneNum').removeClass("hidden");
            $('.phone-num').removeClass("hidden");
            $('.phoneNum').html($phone.phoneFormat(phone));
        };

        //隐藏电话号码
        this.hiddenPhoneNum = function () {
            $('.content').removeClass("max");
            $(".navbar-phone").width("60%");
            $('.phoneNum').addClass("hidden");
            $('.phone-num').addClass("hidden");
            $('.phoneNum').html("");
        };

        this.phoneFormat= function(phone){
            if(phone.length <= 3 ){
                return phone;
            }
            return phone.slice(0,3) + (phone.slice(3) === "" ? "":(" " + $phone.phoneFormatMethd(phone.slice(3))));
        };

        this.phoneFormatMethd = function(phone4){
            return phone4.slice(0,4) + (phone4.slice(4) === "" ? "":(" " + $phone.phoneFormatMethd(phone4.slice(4))));
        };

        /**
         * 计算并显示状态持续时长
         * */
        this.showStatusDuration = function(){
            $phone.statusTimer = window.setInterval(function(){
                $phone.durationTime ++;
                var second = parseInt($phone.durationTime);//秒
                var minute = 0;	//分
                var hour = 0;	//小时
                if(second >= 60) {
                    minute = parseInt(second/60);
                    second = parseInt(second%60);
                    if(minute >= 60) {
                        hour = parseInt(minute/60);
                        minute = parseInt(minute%60);
                    }
                }
                var result = "00:00:"+parseInt(second);
                second = parseInt(second);
                if(parseInt(second) < 10){
                    result = "00:00:0"+parseInt(second);
                    second = "0" + parseInt(second)
                }
                if(minute > 0) {
                    result = "00:" + parseInt(minute) + ":" + second;
                    minute = parseInt(minute);
                }
                if(parseInt(minute) < 10){
                    result = "00:0" + parseInt(minute) + ":" + second;
                    minute = "0" + parseInt(minute);
                }
                if(hour > 0) {
                    result = ""+parseInt(hour) + ":" + minute + ":" + second;
                }
                if(parseInt(hour) < 10){
                    result = "0"+parseInt(hour) + ":" + minute + ":" + second;
                }
                $(".statusDuration").html(result);
            },1000);
        };

        this.showPhoneByEvent = function (statusName,showButton,hiddenButton) {
            $('.showStatus').html(statusName);

            if(showButton){
                let statusArr = showButton.split("|");
                $.each(statusArr,function(i, status){
                    $('#li-' + status ).attr("class", "show");
                });
            }

            if(hiddenButton){
                let hiddenArr = hiddenButton.split("|");
                $.each(hiddenArr,function(i, status){
                    $('#li-' + status ).attr("class", "hidden");
                });
            }
        };



        $phone = this;//获得本类的引用
    };

    var tmp = '\n' +
        '<nav class="navbar navbar-phone" style="height: 80px;width: 60%;">\n' +
        '    <div class="container">\n' +
        '         <div class="navbar-collapse">\n' +
        '            <ul class="nav navbar-nav">\n' +
        '                <li id="li-logout" class="hidden">\n' +
        '\t\t\t\t\t<a href="#" class="logoff logout">\n' +
        '\t\t\t\t\t\t<span>签出</span>\n' +
        '\t\t\t\t\t</a>\n' +
        '                </li>\n' +
        '                <li id="li-answer" class="hidden">\n' +
        '                    <a href="#" class="answer">\n' +
        '                        <span>接听</span>\n' +
        '                    </a>\n' +
        '                </li>\n' +
        '\t\t\t\t<li id="li-dial" class="hidden">\n' +
        '\t\t\t\t\t<a href="#" class="dial">\n' +
        '\t\t\t\t\t\t<span>拨号</span>\n' +
        '\t\t\t\t\t</a>\n' +
        '\t\t\t\t</li>\n' +
        '\n' +
        '\t\t\t\t<li id="li-hold" class="hidden">\n' +
        '\t\t\t\t\t<a href="#" class="hold">\n' +
        '\t\t\t\t\t\t<span>保持</span>\n' +
        '\t\t\t\t\t</a>\n' +
        '\t\t\t\t</li>\n' +
        '\n' +
        '\t\t\t\t<li id="li-unHold" class="hidden">\n' +
        '\t\t\t\t\t<a href="#" class="unHold">\n' +
        '\t\t\t\t\t\t<span>取消</span>\n' +
        '\t\t\t\t\t</a>\n' +
        '\t\t\t\t</li>\n' +
        '\n' +
        '\t\t\t\t<li id="li-consult" class="hidden">\n' +
        '\t\t\t\t\t<a href="#" class="consult">\n' +
        '\t\t\t\t\t\t<span>咨询</span>\n' +
        '\t\t\t\t\t</a>\n' +
        '\t\t\t\t</li>\n' +
        '\t\t\t\t<li id="li-transfer" class="hidden">\n' +
        '\t\t\t\t\t<a href="#" class="transfer">\n' +
        '\t\t\t\t\t\t<span>转接</span>\n' +
        '\t\t\t\t\t</a>\n' +
        '\t\t\t\t</li>\n' +
        '\t\t\t\t<li id="li-conference" class="hidden">\n' +
        '\t\t\t\t\t<a href="#" class="conference">\n' +
        '\t\t\t\t\t\t<span>会议</span>\n' +
        '\t\t\t\t\t</a>\n' +
        '\t\t\t\t</li>\n' +
        '\t\t\t\t<li id="li-hangup" class="hidden">\n' +
        '\t\t\t\t\t<a href="#" class="hangup">\n' +
        '\t\t\t\t\t\t<span>挂断</span>\n' +
        '\t\t\t\t\t</a>\n' +
        '\t\t\t\t</li>\n' +
        '\t\t\t\t<li id="li-acw" class="hidden">\n' +
        '\t\t\t\t\t<a href="#" class="acw">\n' +
        '\t\t\t\t\t\t<span>处理</span>\n' +
        '\t\t\t\t\t</a>\n' +
        '\t\t\t\t</li>\n' +
        '\n' +
        '                <li class="content">\n' +
        '\t\t\t\t\t<span class="content-span showStatus"> 未登录 </span>\n' +
        '\t\t\t\t\t<span class="content-span">|</span><span class="phoneNum hidden"> 159 8675 7872 </span>\n' +
        '\t\t\t\t\t<span class="content-span phone-num hidden"> | </span><span class="statusDuration"> 00:00:00 </span>\n' +
        '                </li>\n' +
        '                <li id="li-busy" class="hidden">\n' +
        '                    <a href="#" class="busy">\n' +
        '\t\t\t\t\t\t<span>示忙</span>\n' +
        '                    </a>\n' +
        '                </li>\n' +
        '\t\t\t\t<li id="li-idle" class="hidden">\n' +
        '\t\t\t\t\t<a href="#" class="idle" >\n' +
        '\t\t\t\t\t\t<span>示闲</span>\n' +
        '\t\t\t\t\t</a>\n' +
        '\t\t\t\t</li>\n' +
        '\n' +
        '                <li id="li-rest" class="hidden">\n' +
        '                    <a href="#" class="rest">\n' +
        '\t\t\t\t\t\t<span>小休</span>\n' +
        '                    </a>\n' +
        '                </li>\n' +
        '\n' +
        '\t\t\t\t<li id="li-monitor" class="hidden">\n' +
        '\t\t\t\t\t<a href="#" class="monitor">\n' +
        '\t\t\t\t\t\t<span>管理</span>\n' +
        '\t\t\t\t\t</a>\n' +
        '\t\t\t\t</li>\n' +
        '            </ul>\n' +
        '        </div>\n' +
        '    </div>\n' +
        '</nav>';


})(jQuery);