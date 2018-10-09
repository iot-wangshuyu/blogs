<#compress >
<#include "module/_macro.ftl">
<@head title="关于Lit"></@head>
<div class="wrapper">
    <!-- 顶部栏模块 -->
    <#include "module/_header.ftl">
    <!-- 菜单栏模块 -->
    <#include "module/_sidebar.ftl">
    <div class="content-wrapper">
        <style type='text/css'>
            blockquote{
                border-left: 4px solid #dddddd;
                padding: 0 15px;
                color: #777777;
                font-size: 16px;
            }
        </style>
        <section class="content-header">
            <h1 style="display: inline-block;">关于Little Tree</h1>
            <ol class="breadcrumb">
                <li>
                    <a data-pjax="true" href="/admin"><i class="fa fa-dashboard"></i> 首页</a>
                </li>
                <li class="active">关于Little Tree</li>
            </ol>
        </section>
        <section class="content container-fluid">
            <div id='write' class='is-mac'>
                <blockquote style="font-size: 14px;">
                    <p>最好的Java博客系统😉</p>
                    <p>非常感谢你使用Little Tree进行创作。</p>
                </blockquote>
                <p>如果在使用过程中出现bug或者无法解决的问题，希望各位在使用过程中及时向我反馈：</p>
                <p>Blog : <a href="https://ryanc.cc" target="_blank">https://ryanc.cc</a> </p>
                <p>Email : <a href='iot_wangshuyu@126.com'>i@ryanc.cc</a></p>
                <p>QQ : 1225470167</p>
            </div>
        </section>
    </div>
    <#include "module/_footer.ftl">
</div>
<@footer></@footer>
</#compress>
