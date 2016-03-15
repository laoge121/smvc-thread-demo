/**
 * Created by pei.xu on 2015/10/26.
 */
var host = "http://localhost:8080";

var menusurl = host + "/mvc/menus/create.do?tmp=" + new Date().getTime();


$(function () {

    $('.demo-cancel-click').click(function () {
        return false;
    });

    loadMenus();

    /**
     * 加载菜单
     */
    function loadMenus() {
        $.ajax({
            url: menusurl,
            type: "POST",
            cache: false,
            data: {},
            dataType: 'json',
            success: function (result) {

                var menus = [];

                if (result.ret) {

                    $(result.data).each(function (index, item) {
                        var obj = '<a href="#accounts-menu' + index + '" class="nav-header" data-toggle="collapse"><i class="icon-briefcase"></i>' + item.menuname + '<i class="icon-chevron-up"></i></a>\
                        <ul id="accounts-menu' + index + '" class="nav nav-list collapse">';
                        $(item.childMenus).each(function (index1, item1) {
                            obj += '<li>\
                                         <a href="' + item1.menuurl + '">' + item1.menuname + '</a>\
                                    </li>';
                        });
                        obj += '</ul>';
                        menus.push(obj);
                    });
                    $("#j_menus").html(menus.join(''));
                }
            }, error: function (result) {
            }
        });
    };
});
