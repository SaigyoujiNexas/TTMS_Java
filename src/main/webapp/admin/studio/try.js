function FormatDateTime(UnixTime) {  // UnixTime要的是一个字符串，格式传错了就会出错
    var a = UnixTime.replace("/Date(", "").replace(")/", "");
    var date = new Date(parseInt(a));
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    m = m < 10 ? ('0' + m) : m;
    var d = date.getDate();
    d = d < 10 ? ('0' + d) : d;
    var h = date.getHours();
    h = h < 10 ? ('0' + h) : h;
    var minute = date.getMinutes();
    var second = date.getSeconds();
    minute = minute < 10 ? ('0' + minute) : minute;
    second = second < 10 ? ('0' + second) : second; 
     let time = y + '-' + m + '-' + d + ' ' + h + ':' + minute + ':' + second;       
     return time
}
const num = 1652776993000
console.log(FormatDateTime(num.toString()));
