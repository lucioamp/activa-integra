
	String.prototype.clearNull = function () {
		return (this == "null" ? ''.trim() : this).replaceAll("%#!", "\n").toString();
	};

	String.prototype.trim = function () {
		return this.replace(/^\s+|\s+$/g,"");
	};
 
	//left trim
	String.prototype.ltrim = function () {
		return this.replace(/^\s+/,"");
	};
 
	//right trim
	String.prototype.rtrim = function () {
		return this.replace(/\s+$/,"");
	};
	
	String.prototype.substr_count = function (substring, start, length)
	{
		var string = "";
		var c = 0;
		if(start) { string = this.substr(start); }
		if(length) { string = this.substr(0,length); }
		
		for (var i=0;i<this.length;i++)
		{
			if(substring == this.substr(i, substring.length))
				c++;
		}
		
		return c;
	};
	
	String.prototype.isNull =  function (x){
       return !(this.toString() !="" && !(this.match(/^\s+$/)));
	};
	
	String.prototype.replaceAll = function(de, para){
	    var str = this;
	    var pos = str.indexOf(de);
	    while (pos > -1){
			str = str.replace(de, para);
			pos = str.indexOf(de);
		}
	    return (str);
	};