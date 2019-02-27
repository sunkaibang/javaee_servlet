function addDay(year, month, day) {
	var oYearNode = document.getElementById(year).value;
	var oMonthNode = document.getElementById(month).value;
	var oDayNode = document.getElementById(day);
	var sDayTime = oDayNode.value;
	oDayNode.length = 0;
	for(var i = 1; i <= 28; i++) {
		var oDay = document.createElement("option");
		oDay.innerHTML = i;
		oDayNode.appendChild(oDay);
	}
	if(oYearNode % 400 == 0 || (oYearNode % 4 == 0 && oYearNode % 100 != 0)) {
		if(oMonthNode == 2) {
			var oDay = document.createElement("option");
			oDay.innerHTML = 29;
			oDayNode.appendChild(oDay);
		} else if(oMonthNode == 4 || oMonthNode == 6 || oMonthNode == 9 || oMonthNode == 11) {
			for(var i = 29; i <= 30; i++) {
				var oDay = document.createElement("option");
				oDay.innerHTML = i;
				oDayNode.appendChild(oDay);
			}
		} else {
			for(var i = 29; i <= 31; i++) {
				var oDay = document.createElement("option");
				oDay.innerHTML = i;
				oDayNode.appendChild(oDay);
			}
		}
	} else {
		if(oMonthNode == 2) {} else if(oMonthNode == 4 || oMonthNode == 6 || oMonthNode == 9 || oMonthNode == 11) {
			for(var i = 29; i <= 30; i++) {
				var oDay = document.createElement("option");
				oDay.innerHTML = i;
				oDayNode.appendChild(oDay);
			}
		} else {
			for(var i = 29; i <= 31; i++) {
				var oDay = document.createElement("option");
				oDay.innerHTML = i;
				oDayNode.appendChild(oDay);
			}
		}
	}
	oDayNode.selectedIndex = parseInt(sDayTime - 1);
}

function initDate(year, month, day) {
	var dDate = new Date();
	var dEndYear = dDate.getFullYear();
	var dEndMonth = dDate.getMonth();
	var dEndDay = dDate.getDate();
	var oYearNode = document.getElementById(year);
	for(var i = dEndYear; i >= 1960; i--) {
		var oYear = document.createElement("option");
		oYear.innerHTML = i;
		oYearNode.appendChild(oYear);
	}

	var dMonthNode = document.getElementById(month);
	dMonthNode.length = 0;
	for(var i = 1; i <= 12; i++) {
		var oMonth = document.createElement("option");
		oMonth.innerHTML = i;
		dMonthNode.appendChild(oMonth);
	}
	dMonthNode.selectedIndex = dEndMonth;

	var dDayNode = document.getElementById(day);
	addDay(year, month, day);
	dDayNode.selectedIndex = dEndDay - 1;
}