function createXMLHttpRequest() {
	try {
		return new XMLHttpRequest();
	} catch (e) {
		try {
			return new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				return new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
				alert("ªª“ª∏ˆ‰Ø¿¿∆˜ ‘ ‘∞…!");
				throw e;
			}
		}
	}
}