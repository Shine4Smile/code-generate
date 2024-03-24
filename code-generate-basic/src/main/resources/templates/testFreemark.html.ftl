<!DOCTYPE html>
<html>
    <head>模板引擎测试</head>
    <body>
        <h1>中国一线城市排名top${cityList?size}</h1>
        <ul>
        <#list cityList as city>
            <li>城市名称：${city.name} 备注：${city.remark}</li>
        </#list>
        </ul>
        <footer>
                ${currentTime} · 最新榜单
        </footer>
    </body>
</html>