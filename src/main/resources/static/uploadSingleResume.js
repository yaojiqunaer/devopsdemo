
layui.use(['element','laydate','form','layer','upload','laytpl','myfunc'],function(){
	var upload = layui.upload,
	form = layui.form,
	layer = layui.layer,
	laydate = layui.laydate,
	element = layui.element,
	$ = layui.jquery,
	func = layui.myfunc,
	laytpl = layui.laytpl;

	laydate.render({
		elem: '#workStartYear'
		,trigger: 'click'//呼出事件改成click防止闪退
	    ,type: 'year'
	});
	laydate.render({
		elem: '#eduStart'
		,trigger: 'click'//呼出事件改成click防止闪退
	    ,type: 'month'
	});
	laydate.render({
		elem: '#eduEnd'
		,trigger: 'click'//呼出事件改成click防止闪退
	    ,type: 'month'
	});
	laydate.render({
		elem: '#workStart'
		,trigger: 'click'//呼出事件改成click防止闪退
	    ,type: 'month'
	});
	laydate.render({
		elem: '#workEnd'
		,trigger: 'click'//呼出事件改成click防止闪退
	    ,type: 'month'
	});
	laydate.render({
		elem: '#socialStart'
		,trigger: 'click'//呼出事件改成click防止闪退
	    ,type: 'month'
	});
	laydate.render({
		elem: '#socialEnd'
		,trigger: 'click'//呼出事件改成click防止闪退
	    ,type: 'month'
	});
	laydate.render({
		elem: '#projectStart'
		,trigger: 'click'//呼出事件改成click防止闪退
	    ,type: 'month'
	});
	laydate.render({
		elem: '#projectEnd'
		,trigger: 'click'//呼出事件改成click防止闪退
	    ,type: 'month'
	});
	laydate.render({
		elem: '#trainingStart'
		,trigger: 'click'//呼出事件改成click防止闪退
	    ,type: 'month'
	});
	laydate.render({
		elem: '#trainingEnd'
		,trigger: 'click'//呼出事件改成click防止闪退
	    ,type: 'month'
	});

	//聚焦失焦显示边框
	$("input").focus(function(){
		$(this).removeAttr("style");
	})
	$("input").blur(function(){
		$(this).css({"border": "0", "background-color": "transparent" });
	});

	//表单提交
	$("#addResume").click(function() {
        //获取上传数据
        var form=getForm();
        if(form.basicInfo.name==""){
            alert("请填写姓名");
            return;
        }
		//检查简历重复
		$.ajax({
			url:"/resume/resume/selectSimilar",
			type:"post",
			dataType:"json",
			async:true,
			contentType:"application/json",
			data:JSON.stringify(form),
			success:function(data){
				if (data.code==0) {
					//在这里面输入任何合法的js语句
				/*	layer.open({
						type: 1 //Page层类型
						,area: ['600px', '400px']
						,title: '警告！！！'
						,shade: 0.8 //遮罩透明度
						,maxmin: true //允许全屏最小化
						,anim: 2 //0-6的动画形式，-1不开启
						,content: '<div style="padding-top:20px;padding-left:20% ">检测到'+data.count+
							'份相似简历</div>'+
							+'<button id="save" class="layui-btn">继续保存</button>'
							+'<script>' +

							'</script>'
					});*/

					//询问框
					var confirm=layer.confirm('检测到相似的简历'+data.count+'份，是否继续上传', {
						btn: ['查看详情',"仍然保存"] //按钮
					}, function(){
						//有重复简历 查看详情
						var ids=[];
						var similars=[];
						for (var i=0;i<data.count;i++){
							ids.push(data.data[i].id);
							similars.push(data.data[i].similarity);
						}
						func.open({
							title: '相似简历',
							content: Feng.ctxPath + '/resume/similar?ids='+ids+'&similars='+similars,
						});
						layer.close(confirm);
						//window.open("/resume/resume/similar");
					}, function(){
						addResume();
						/*layer.msg('也可以这样', {
							time: 20000, //20s后自动关闭
							btn: ['明白了', '知道了']
						});*/
					});
				}
				if (data.code==-1) {
					//无重复简历 执行添加
					addResume();
				}
			}
		});
	});
	$("#look").click(function() {
		window.open("https://view.officeapps.live.com/op/view.aspx?src="+$("#fileurl").val());
	})

	//解析文件
	function parseResume(fileurl,filename) {
		//上传
		$.ajax({
			type:'post',
			data:{"filename":filename,"fileurl":fileurl},
			dataType:"json",
			url:"/resume/parse/parseResume",
			success: function(data){
				if (data.code==0) {
					//表单赋值
					setForm(data);
					form.render();
					//弹出消息成功
					layer.msg("OK");
				}
				//解析失败
				if (data.code!=0) {
					layer.msg(data.msg, {
						icon: 5,
			    		time: 3000 //2秒关闭（如果不配置，默认是3秒）
			    	});
				}
			}
		})

	}

	//拖拽上传 异步请求上传——》七牛云
	var uploadIns=upload.render({
		elem: '#uploadContainer'
		,accept: 'file'
		,size: 2048
		,acceptMime: 'text/html,text/plain,application/pdf,application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document'
		,exts: 'html|txt|pdf|doc|docx'
		,url: '/resume/upload/uploadSingle' //改成您自己的上传接口
		,before: function(obj){ //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
		  	$("#up").hide();
			//加载层-风格4
		  	layer.msg('正在上传文件...', {
		  		icon: 16,
			  	shade: 0.3,
			  	time: 2000 //2秒关闭（如果不配置，默认是3秒）
		  	}, function(){
		  	  //do something
		  	});

		}
		,done: function(res, index, upload){
		    if(res.code == 200){
		    	//加载层-风格4
		    	var index2 = top.layer.msg('正在解析，请稍后...',{icon: 16,time:false,shade:0.8});
		    	var fileurl=res.resultString
		    	,filename=res.filename
		    	,originalFilename=res.originalFilename;
		    	//开始预览
		    	$("#filename").val(filename);
		    	$("#fileurl").val(fileurl);
		    	$("#originalFilename").val(originalFilename);
		    	//预览可见
		    	$(".con-upload-second").css({"display": "block"});
		    	//预览 这个头部效果不好 https://view.officeapps.live.com/op/view.aspx?src=
				$(".iframe").attr({ src:"https://view.officeapps.live.com/op/embed.aspx?src="+fileurl});
		    	//调用函数解析简历
		    	parseResume(fileurl,filename);
		    	top.layer.close(index2);
		    }
		    //文件保存失败
		    if (res.code != 200) {
				$("#up").show();
		    	layer.msg(res.resultMessage, {
		    		  icon: 5,
		    		  time: 3000 //2秒关闭（如果不配置，默认是3秒）
		    	});
			}
		    //获取当前触发上传的元素，一般用于 elem 绑定 class 的情况，注意：此乃 layui 2.1.0 新增
		    var item = this.item;
	    }
	    ,error: function(){
	      //请求异常回调
	    }
	});

	//前台数据封装为json
	function getForm(){
		var json = {};
		//基本信息赋值
		var basicInfo = {};
		basicInfo.name = $("#name").val();
		basicInfo.gender = $("#gender").val();
		basicInfo.dateOfBirth = $("#dateOfBirth").val();
		if($("#age").val() != null && $("#age").val() != "") {
			basicInfo.age = $("#age").val();
		}
		basicInfo.degree = $("#degree").val();
		basicInfo.gender = $("#gender").val();
		basicInfo.major = $("#major").val();
		basicInfo.schoolName = $("#schoolName").val();
		basicInfo.schoolType = $("#schoolType").val();
		basicInfo.nationalIdentityNumber = $("#nationalIdentityNumber").val();
		basicInfo.birthplace = $("#birthplace").val();
		basicInfo.ethnic = $("#ethnic").val();
		basicInfo.politicalStatus = $("#politicalStatus").val();
		basicInfo.currentLocation = $("#currentLocation").val();
		basicInfo.detailedLocation = $("#detailedLocation").val();
		basicInfo.currentCompany = $("#currentCompany").val();
		if($("#numWorkExperience").val() != null && $("#numWorkExperience").val() != "") {
			basicInfo.numWorkExperience = $("#numWorkExperience").val();
		}
		basicInfo.currentPosition = $("#currentPosition").val();
		basicInfo.desiredPosition = $("#desiredPosition").val();
		basicInfo.currentSalary = $("#currentSalary").val();
		basicInfo.desiredSalary = $("#desiredSalary").val();
		basicInfo.industry = $("#industry").val();
		basicInfo.expectLocation = $("#expectLocation").val();
		basicInfo.currentStatus = $("#currentStatus").val();
		basicInfo.maritalStatus = $("#maritalStatus").val();
		basicInfo.zipcode = $("#zipcode").val();
		//联系信息赋值
		var contactInfo = {};
		contactInfo.phoneNumber = $("#phoneNumber").val();
		contactInfo.homePhoneNumber = $("#homePhoneNumber").val();
		contactInfo.email = $("#email").val();
		contactInfo.wechat = $("#wechat").val();
		contactInfo.QQ = $("#qq").val();
		//教育信息赋值
		var educationExperience = {};
		educationExperience.schoolName = $("#eduSchoolName").val();
		educationExperience.schoolLevel = $("#schoolLevel").val();
		educationExperience.schoolRank = $("#schoolRank").val();
		educationExperience.location = $("#location").val();
		educationExperience.department = $("#eduDepartment").val();
		educationExperience.major = $("#eduMajor").val();
		educationExperience.ranking = $("#ranking").val();
		educationExperience.GPA = $("#gpa").val();
		educationExperience.degree = $("#eduDegree").val();
		educationExperience.studyModel = $("#studyModel").val();
		educationExperience.courses = $("#courses").val();
		if($("#stillActive").val() != null && $("#stillActive").val() != "") {
			educationExperience.stillActive = $("#stillActive").val();
		}
		if($("#eduStart").val() != null && $("#eduStart").val() != "") {
			educationExperience.startTimeYear = $("#eduStart").val().split("-")[0];
			educationExperience.startTimeMonth = $("#eduStart").val().split("-")[1];
		}
		if($("#eduEnd").val() != null && $("#eduEnd").val() != "") {
			educationExperience.endTimeYear = $("#eduEnd").val().split("-")[0];
			educationExperience.endTimeMonth = $("#eduEnd").val().split("-")[1];
		}
		//工作信息赋值
		var workExperience = {};
		workExperience.companyName = $("#companyName").val();
		workExperience.location = $("#workLocation").val();
		workExperience.companyType = $("#companyType").val();
		workExperience.companySize = $("#companySize").val();
		workExperience.industry = $("#workIndustry").val();
		workExperience.department = $("#workDepartment").val();
		workExperience.jobTitle = $("#workJobTitle").val();
		if($("#workStillActive").val() != null && $("#workStillActive").val() != "") {
			workExperience.stillActive = $("#workStillActive").val();
		}
		workExperience.jobTitle = $("#workJobTitle").val();
		if($("#workStart").val() != null && $("#workStart").val() != "") {
			workExperience.startTimeYear = $("#workStart").val().split("-")[0];
			workExperience.startTimeMonth = $("#workStart").val().split("-")[1];
		}
		if($("#workEnd").val() != null && $("#workEnd").val() != "") {
			workExperience.endTimeYear = $("#workEnd").val().split("-")[0];
			workExperience.endTimeMonth = $("#workEnd").val().split("-")[1];
		}
		workExperience.description = $("#workDescription").val();
		//获取社会信息
		var socialExperience = {};
		socialExperience.organizationName = $("#socialOrganizationName").val();
		socialExperience.location = $("#socialLocation").val();
		socialExperience.department = $("#socialDepartment").val();
		socialExperience.jobTitle = $("#socialJobTitle").val();
		if($("#socialStart").val() != null && $("#socialStart").val() != "") {
			socialExperience.startTimeYear = $("#socialStart").val().split("-")[0];
			socialExperience.startTimeMonth = $("#socialStart").val().split("-")[1];
		}
		if($("#socialEnd").val() != null && $("#socialEnd").val() != "") {
			socialExperience.endTimeYear = $("#socialEnd").val().split("-")[0];
			socialExperience.endTimeMonth = $("#socialEnd").val().split("-")[1];
		}
		if($("#socialStillActive").val() != null && $("#socialStillActive").val() != "") {
			socialExperience.stillActive = $("#socialStillActive").val();
		}
		socialExperience.description = $("#socialDescription").val();
		//获取项目信息
		var projectExperience = {};
		projectExperience.projectName = $("#projectName").val();
		projectExperience.location = $("#projectLocation").val();
		projectExperience.jobTitle = $("#projectJobTitle").val();
		if($("#projectStart").val() != null && $("#projectStart").val() != "") {
			projectExperience.startTimeYear = $("#projectStart").val().split("-")[0];
			projectExperience.startTimeMonth = $("#projectStart").val().split("-")[1];
		}
		if($("#projectEnd").val() != null && $("#projectEnd").val() != "") {
			projectExperience.endTimeYear = $("#projectEnd").val().split("-")[0];
			projectExperience.endTimeMonth = $("#projectEnd").val().split("-")[1];
		}
		if($("#projectStillActive").val() != null && $("#projectStillActive").val() != "") {
			projectExperience.stillActive = $("#projectStillActive").val();
		}
		projectExperience.description = $("#projectDescription").val();
		//获取培训信息
		var trainingExperience = {};
		trainingExperience.organizationName = $("#trianingOrganizationName").val();
		trainingExperience.location = $("#trianingLocation").val();
		trainingExperience.subject = $("#subject").val();
		if($("#trainingStillActive").val() != null && $("#trainingStillActive").val() != "") {
			trainingExperience.stillActive = $("#trainingStillActive").val();
		}
		if($("#trainingStart").val() != null && $("#trainingStart").val() != "") {
			trainingExperience.startTimeYear = $("#trainingStart").val().split("-")[0];
			trainingExperience.startTimeMonth = $("#trainingStart").val().split("-")[1];
		}
		if($("#trainingEnd").val() != null && $("#trainingEnd").val() != "") {
			trainingExperience.endTimeYear = $("#trainingEnd").val().split("-")[0];
			trainingExperience.endTimeMonth = $("#trainingEnd").val().split("-")[1];
		}
		trainingExperience.description = $("#trainingDescription").val();
		//获取补充信息
		var others = {};
		others.skills = $("#skills").val();
		others.itSkills = $("#itSkills").val();
		others.businessSkills = $("#businessSkills").val();
		others.language = $("#language").val();
		others.certificate = $("#certificate").val();
		others.awards = $("#awards").val();
		others.selfEvaluation = $("#selfEvaluation").val();
		//构造json对象
		json.url = $("#fileurl").val();
		json.filename = $("#originalFilename").val();
		json.basicInfo = basicInfo;
		json.contactInfo = contactInfo;
		json.educationExperience = educationExperience;
		json.workExperience = workExperience;
		json.socialExperience = socialExperience;
		json.projectExperience = projectExperience;
		json.trainingExperience = trainingExperience;
		json.others = others;
		//返回json对象
		return json;
	}
	//将后台解析的数据返回  给表单赋值
	function setForm(data){
		//去除表单的输入边框
		$("input").css({"border": "0", "background-color": "transparent" });
		//移除提示
		$("input").removeAttr("placeholder");
		//获取基本信息
		var basicInfo=data.data.basicInfo;
		var name=basicInfo.name;
		var gender=basicInfo.gender;
		var dateOfBirth=basicInfo.dateOfBirth;
		var age=basicInfo.age;
		var degree=basicInfo.degree;
		var major=basicInfo.major;
		var schoolName=basicInfo.schoolName;
		var schoolType=basicInfo.schoolType;
		var nationalIdentityNumber=basicInfo.nationalIdentityNumber;
		var birthplace=basicInfo.birthplace;
		var ethnic=basicInfo.ethnic;
		var politicalStatus=basicInfo.politicalStatus;
		var currentLocation=basicInfo.currentLocation;
		var detailedLocation=basicInfo.detailedLocation;
		var currentCompany=basicInfo.currentCompany;
		var numWorkExperience=basicInfo.numWorkExperience;
		var currentPosition=basicInfo.currentPosition;
		var desiredPosition=basicInfo.desiredPosition;
		var currentSalary=basicInfo.currentSalary;
		var desiredSalary=basicInfo.desiredSalary;
		var industry=basicInfo.industry;
		var expectLocation=basicInfo.expectLocation;
		var workStartYear=basicInfo.workStartYear;
		var currentStatus=basicInfo.currentStatus;
		var maritalStatus=basicInfo.maritalStatus;
		var zipcode=basicInfo.zipcode;
		//基本信息赋值
		if (name!=null&&name!="") {
			$("#name").val(name);
		}
		if (gender!=null&&gender!="") {
			$("#gender").val(gender);
		}
		if (dateOfBirth!=null&&dateOfBirth!="") {
			$("#dateOfBirth").val(dateOfBirth);
		}
		if (age!=null&&age!="") {
			$("#age").val(age);
		}
		if (degree!=null&&degree!="") {
			$("#degree").val(degree);
		}
		if (gender!=null&&gender!="") {
			$("#gender").val(gender);
		}
		if (major!=null&&major!="") {
			$("#major").val(major);
		}
		if (schoolName!=null&&schoolName!="") {
			$("#schoolName").val(schoolName);
		}
		if (schoolType!=null&&schoolType!="") {
			$("#schoolType").val(schoolType);
		}
		if (nationalIdentityNumber!=null&&nationalIdentityNumber!="") {
			$("#nationalIdentityNumber").val(nationalIdentityNumber);
		}
		if (birthplace!=null&&birthplace!="") {
			$("#birthplace").val(birthplace);
		}
		if (ethnic!=null&&ethnic!="") {
			$("#ethnic").val(ethnic);
		}
		if (politicalStatus!=null&&politicalStatus!="") {
			$("#politicalStatus").val(politicalStatus);
		}
		if (currentLocation!=null&&currentLocation!="") {
			$("#currentLocation").val(currentLocation);
		}
		if (detailedLocation!=null&&detailedLocation!="") {
			$("#detailedLocation").val(detailedLocation);
		}
		if (currentCompany!=null&&currentCompany!="") {
			$("#currentCompany").val(currentCompany);
		}
		if (numWorkExperience!=null&&numWorkExperience!="") {
			$("#numWorkExperience").val(numWorkExperience);
		}
		if (currentPosition!=null&&currentPosition!="") {
			$("#currentPosition").val(currentPosition);
		}
		if (desiredPosition!=null&&desiredPosition!="") {
			$("#desiredPosition").val(desiredPosition);
		}
		if (currentSalary!=null&&currentSalary!="") {
			$("#currentSalary").val(currentSalary);
		}
		if (desiredSalary!=null&&desiredSalary!="") {
			$("#desiredSalary").val(desiredSalary);
		}
		if (industry!=null&&industry!="") {
			$("#industry").val(industry);
		}
		if (expectLocation!=null&&expectLocation!="") {
			$("#expectLocation").val(expectLocation);
		}
		if (workStartYear!=null&&workStartYear!="") {
			$("#workStartYear").val(workStartYear);
		}
		if (currentStatus!=null&&currentStatus!="") {
			$("#currentStatus").val(currentStatus);
		}
		if (maritalStatus!=null&&maritalStatus!="") {
			$("#maritalStatus").val(maritalStatus);
		}
		if (zipcode!=null&&zipcode!="") {
			$("#zipcode").val(zipcode);
		}
		//获取联系信息
		var contactInfo=data.data.contactInfo;
		var phoneNumber=contactInfo.phoneNumber;
		var homePhoneNumber=contactInfo.homePhoneNumber;
		var email=contactInfo.email;
		var wechat=contactInfo.wechat;
		var qq=contactInfo.qQ;
		//联系信息赋值
		if (phoneNumber!=null&&phoneNumber!="") {
			$("#phoneNumber").val(phoneNumber);
		}
		if (homePhoneNumber!=null&&homePhoneNumber!="") {
			$("#homePhoneNumber").val(homePhoneNumber);
		}
		if (email!=null&&email!="") {
			$("#email").val(email);
		}
		if (wechat!=null&&wechat!="") {
			$("#wechat").val(wechat);
		}
		if (qq!=null&&qq!="") {
			$("#qq").val(qq);
		}
		//获取教育信息
		var educationExperience=data.data.educationExperience;
		if (educationExperience!=null&&educationExperience!="") {
			var eduSchoolName=educationExperience.schoolName;
			var schoolLevel=educationExperience.schoolLevel;
			var schoolRank=educationExperience.schoolRank;
			var location=educationExperience.location;
			var eduDepartment=educationExperience.department;
			var eduMajor=educationExperience.major;
			var ranking=educationExperience.ranking;
			var gpa=educationExperience.gPA;
			var eduDegree=educationExperience.degree;
			var studyModel=educationExperience.studyModel;
			var courses=educationExperience.courses;
			var stillActive=educationExperience.stillActive;
			var eduStart=educationExperience.startTimeYear+"-"+educationExperience.startTimeMonth;
			var eduEnd=educationExperience.endTimeYear+"-"+educationExperience.endTimeMonth;
			//教育信息赋值
			if (eduSchoolName!=null&&eduSchoolName!="") {
				$("#eduSchoolName").val(eduSchoolName);
			}
			if (schoolLevel!=null&&schoolLevel!="") {
				$("#schoolLevel").val(schoolLevel);
			}
			if (schoolRank!=null&&schoolRank!="") {
				$("#schoolRank").val(schoolRank);
			}
			if (location!=null&&location!="") {
				$("#location").val(location);
			}
			if (eduDepartment!=null&&eduDepartment!="") {
				$("#eduDepartment").val(eduDepartment);
			}
			if (eduMajor!=null&&eduMajor!="") {
				$("#eduMajor").val(eduMajor);
			}
			if (ranking!=null&&ranking!="") {
				$("#ranking").val(ranking);
			}
			if (gpa!=null&&gpa!="") {
				$("#gpa").val(gpa);
			}
			if (eduDegree!=null&&eduDegree!="") {
				$("#eduDegree").val(eduDegree);
			}
			if (studyModel!=null&&studyModel!="") {
				$("#studyModel").val(studyModel);
			}
			if (courses!=null&&courses!="") {
				$("#courses").val(courses);
			}
			if (stillActive!=null&&stillActive!="") {
				$("#stillActive").val(stillActive);
			}
			if (eduStart!=null&&eduStart!="") {
				$("#eduStart").val(eduStart);
			}
			if (eduEnd!=null&&eduEnd!="") {
				$("#eduEnd").val(eduEnd);
			}
		}
		//获取工作信息
		var workExperience=data.data.workExperience;
		if (workExperience!=null&&workExperience!="") {
			var companyName=workExperience.companyName;
			var workLocation=workExperience.location;
			var companyType=workExperience.companyType;
			var companySize=workExperience.companySize;
			var workIndustry=workExperience.industry;
			var workDepartment=workExperience.department;
			var workJobTitle=workExperience.jobTitle;
			var workStillActive=workExperience.stillActive;
			var workStart=workExperience.startTimeYear+"-"+workExperience.startTimeMonth;
			var workEnd=workExperience.endTimeYear+"-"+workExperience.endTimeMonth;
			var workDescription=workExperience.description;
			//工作信息赋值
			if (companyName!=null&&companyName!="") {
				$("#companyName").val(companyName);
			}
			if (workLocation!=null&&workLocation!="") {
				$("#workLocation").val(workLocation);
			}
			if (companyType!=null&&companyType!="") {
				$("#companyType").val(companyType);
			}
			if (companySize!=null&&companySize!="") {
				$("#companySize").val(companySize);
			}
			if (workIndustry!=null&&workIndustry!="") {
				$("#workIndustry").val(workIndustry);
			}
			if (workDepartment!=null&&workDepartment!="") {
				$("#workDepartment").val(workDepartment);
			}
			if (workJobTitle!=null&&workJobTitle!="") {
				$("#workJobTitle").val(workJobTitle);
			}
			if (workStillActive!=null&&workStillActive!="") {
				$("#workStillActive").val(workStillActive);
			}
			if (workStart!=null&&workStart!="-") {
				$("#workStart").val(workStart);
			}
			if (workEnd!=null&&workEnd!="-") {
				$("#workEnd").val(workEnd);
			}
			if (workDescription!=null&&workDescription!="") {
				$("#workDescription").val(workDescription);
			}
		}
		//获取社会信息
		var socialExperience=data.data.socialExperience;
		if (socialExperience!=null&&socialExperience!="") {
			var socialOrganizationName=socialExperience.organizationName;
			var socialLocation=socialExperience.location;
			var socialDepartment=socialExperience.department;
			var socialJobTitle=socialExperience.jobTitle;
			var socialStart=socialExperience.startTimeYear+"-"+socialExperience.startTimeMonth;
			var socialEnd=socialExperience.endTimeYear+"-"+socialExperience.endTimeMonth;
			var socialStillActive=socialExperience.stillActive;
			var socialDescription=socialExperience.description;
			//工作信息赋值
			if (socialOrganizationName!=null&&socialOrganizationName!="") {
				$("#socialOrganizationName").val(socialOrganizationName);
			}
			if (socialLocation!=null&&socialLocation!="") {
				$("#socialLocation").val(socialLocation);
			}
			if (socialDepartment!=null&&socialDepartment!="") {
				$("#socialDepartment").val(socialDepartment);
			}
			if (socialJobTitle!=null&&socialJobTitle!="") {
				$("#socialJobTitle").val(socialJobTitle);
			}
			if (socialStart!=null&&socialStart!="-") {
				$("#socialStart").val(socialStart);
			}
			if (socialEnd!=null&&socialEnd!="-") {
				$("#socialEnd").val(socialEnd);
			}
			if (socialStillActive!=null&&socialStillActive!="") {
				$("#socialStillActive").val(socialStillActive);
			}
			if (socialDescription!=null&&socialDescription!="") {
				$("#socialDescription").val(socialDescription);
			}
		}
		//获取项目信息
		var projectExperience=data.data.projectExperience;
		if (projectExperience!=null&&projectExperience!="") {
			var projectName=projectExperience.projectName;
			var projectLocation=projectExperience.location;
			var projectJobTitle=projectExperience.jobTitle;
			var projectStart=projectExperience.startTimeYear+"-"+projectExperience.startTimeMonth;
			var projectEnd=projectExperience.endTimeYear+"-"+projectExperience.endTimeMonth;
			var projectStillActive=projectExperience.stillActive;
			var projectDescription=projectExperience.description;
			//项目信息赋值
			if (projectName!=null&&projectName!="") {
				$("#projectName").val(projectName);
			}
			if (projectLocation!=null&&projectLocation!="") {
				$("#projectLocation").val(projectLocation);
			}
			if (projectJobTitle!=null&&projectJobTitle!="") {
				$("#projectJobTitle").val(projectJobTitle);
			}
			if (projectStart!=null&&projectStart!="-") {
				$("#projectStart").val(projectStart);
			}
			if (projectEnd!=null&&projectEnd!="-") {
				$("#projectEnd").val(projectEnd);
			}
			if (projectStillActive!=null&&projectStillActive!="") {
				$("#projectStillActive").val(projectStillActive);
			}
			if (projectDescription!=null&&projectDescription!="") {
				$("#projectDescription").val(projectDescription);
			}
		}
		//获取培训信息
		var trainingExperience=data.data.trainingExperience;
		if (trainingExperience!=null&&trainingExperience!="") {
			var trianingOrganizationName=trainingExperience.organizationName;
			var trianingLocation=trainingExperience.location;
			var subject=trainingExperience.subject;
			var trainingStillActive=trainingExperience.stillActive;
			var trainingStart=trainingExperience.startTimeYear+"-"+trainingExperience.startTimeMonth;
			var trainingEnd=trainingExperience.endTimeYear+"-"+trainingExperience.endTimeMonth;
			var trainingDescription=trainingExperience.description;
			//培训信息赋值
			if (trianingOrganizationName!=null&&trianingOrganizationName!="") {
				$("#trianingOrganizationName").val(trianingOrganizationName);
			}
			if (trianingLocation!=null&&trianingLocation!="") {
				$("#trianingLocation").val(trianingLocation);
			}
			if (subject!=null&&subject!="") {
				$("#subject").val(subject);
			}
			if (trainingStillActive!=null&&trainingStillActive!="") {
				$("#trainingStillActive").val(trainingStillActive);
			}
			if (trainingStart!=null&&trainingStart!="-") {
				$("#trainingStart").val(trainingStart);
			}
			if (trainingEnd!=null&&trainingEnd!="-") {
				$("#trainingEnd").val(trainingEnd);
			}
			if (trainingDescription!=null&&trainingDescription!="") {
				$("#trainingDescription").val(trainingDescription);
			}
		}
		//获取补充信息
		var others=data.data.others;
		if (others!=null&&others!="") {
			var skills=others.skills;
			var itSkills=others.itSkills;
			var businessSkills=others.businessSkills;
			var language=others.language;
			var certificate=others.certificate;
			var awards=others.awards;
			var selfEvaluation=others.selfEvaluation;
			if (skills!=null&&skills!="") {
				$("#skills").val(skills);
			}
			if (itSkills!=null&&itSkills!="") {
				$("#itSkills").val(itSkills);
			}
			if (businessSkills!=null&&businessSkills!="") {
				$("#businessSkills").val(businessSkills);
			}
			if (language!=null&&language!="") {
				$("#language").val(language);
			}
			if (certificate!=null&&certificate!="") {
				$("#certificate").val(certificate);
			}
			if (awards!=null&&awards!="") {
				$("#awards").val(awards);
			}
			if (selfEvaluation!=null&&selfEvaluation!="") {
				$("#selfEvaluation").val(selfEvaluation);
			}
		}
	}
	//添加简历，数据库持久化 from为json对象
	function addResume(){
        //获取上传数据
        var form=getForm();
		//弹出loading
		var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
		//ajax上传
		$.ajax({
			url:"/resume/resume/addResume",
			type:"post",
			dataType:"json",
			contentType:"application/json",
			data:JSON.stringify(form),
			success:function(data){
				if (data.code==0) {
					setTimeout(function(){
						top.layer.close(index);
						top.layer.msg(data.msg);
						layer.closeAll("iframe");
						//刷新父页面
						parent.location.reload();
						//使用parent找到父级，重载数据表格
						parent.layui.table.reload('resumeListTable');
					},2000);
				}
				if (data.code!=0) {
					layer.msg(data.msg, {
						icon: 5,
						time: 1500 //2秒关闭（如果不配置，默认是3秒）
					});
				}
			}
		});
	}

})
