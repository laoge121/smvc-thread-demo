<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>货物管理</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="stylesheet" type="text/css" href="/plug/bootstrap/css/bootstrap.css">

    <link rel="stylesheet" type="text/css" href="/css/theme.css">
    <link rel="stylesheet" href="/plug/font-awesome/css/font-awesome.css">

    <script src="/plug/jquery-1.7.2.min.js" type="text/javascript"></script>
    <!-- Demo page code -->

</head>

<!--[if lt IE 7 ]>
<body class="ie ie6"> <![endif]-->
<!--[if IE 7 ]>
<body class="ie ie7 "> <![endif]-->
<!--[if IE 8 ]>
<body class="ie ie8 "> <![endif]-->
<!--[if IE 9 ]>
<body class="ie ie9 "> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<body class="">
<!--<![endif]-->
<div class="content">

    <div class="header">

        <h1 class="page-title">GOODS</h1>
    </div>

    <ul class="breadcrumb">
        <li><a href="index.html">GOOGS</a> <span class="divider">/</span></li>
        <li class="active">Manage</li>
    </ul>

    <div class="container-fluid">
        <div class="row-fluid">

            <div class="btn-toolbar">
                <button class="btn btn-primary"><i class="icon-plus"></i>新商品</button>
                <button class="btn">导入</button>
                <button class="btn">导出</button>
                <div class="btn-group">
                </div>
            </div>
            <div class="well">
                <table class="table">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>货物名称</th>
                        <th>价格（元）</th>
                        <th>库存（个）</th>
                        <th>简介</th>
                        <th>详细</th>
                        <th style="width: 26px;">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>1</td>
                        <td>Mark</td>
                        <td>Tompson</td>
                        <td>the_mark7</td>
                        <td>
                            <a href="user.html"><i class="icon-pencil"></i></a>
                            <a href="#myModal" role="button" data-toggle="modal"><i class="icon-remove"></i></a>
                        </td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>Ashley</td>
                        <td>Jacobs</td>
                        <td>ash11927</td>
                        <td>
                            <a href="user.html"><i class="icon-pencil"></i></a>
                            <a href="#myModal" role="button" data-toggle="modal"><i class="icon-remove"></i></a>
                        </td>
                    </tr>
                    <tr>
                        <td>3</td>
                        <td>Audrey</td>
                        <td>Ann</td>
                        <td>audann84</td>
                        <td>
                            <a href="user.html"><i class="icon-pencil"></i></a>
                            <a href="#myModal" role="button" data-toggle="modal"><i class="icon-remove"></i></a>
                        </td>
                    </tr>
                    <tr>
                        <td>4</td>
                        <td>John</td>
                        <td>Robinson</td>
                        <td>jr5527</td>
                        <td>
                            <a href="user.html"><i class="icon-pencil"></i></a>
                            <a href="#myModal" role="button" data-toggle="modal"><i class="icon-remove"></i></a>
                        </td>
                    </tr>
                    <tr>
                        <td>5</td>
                        <td>Aaron</td>
                        <td>Butler</td>
                        <td>aaron_butler</td>
                        <td>
                            <a href="user.html"><i class="icon-pencil"></i></a>
                            <a href="#myModal" role="button" data-toggle="modal"><i class="icon-remove"></i></a>
                        </td>
                    </tr>
                    <tr>
                        <td>6</td>
                        <td>Chris</td>
                        <td>Albert</td>
                        <td>cab79</td>
                        <td>
                            <a href="user.html"><i class="icon-pencil"></i></a>
                            <a href="#myModal" role="button" data-toggle="modal"><i class="icon-remove"></i></a>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="pagination">
                <ul>
                    <li><a href="#">Prev</a></li>
                    <li><a href="#">1</a></li>
                    <li><a href="#">2</a></li>
                    <li><a href="#">3</a></li>
                    <li><a href="#">4</a></li>
                    <li><a href="#">Next</a></li>
                </ul>
            </div>

            <div class="modal small hide fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                 aria-hidden="true">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h3 id="myModalLabel">Delete Confirmation</h3>
                </div>
                <div class="modal-body">
                    <p class="error-text"><i class="icon-warning-sign modal-icon"></i>Are you sure you want to delete
                        the user?</p>
                </div>
                <div class="modal-footer">
                    <button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
                    <button class="btn btn-danger" data-dismiss="modal">Delete</button>
                </div>
            </div>

            #include("/admin/footer.jsp")

        </div>
    </div>
</div>
<script src="lib/bootstrap/js/bootstrap.js"></script>
</body>
</html>


