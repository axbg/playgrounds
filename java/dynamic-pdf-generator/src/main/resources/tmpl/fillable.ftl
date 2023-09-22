<!DOCTYPE html>
<html>
<head>
    <title>Welcome Page</title>
    <style>
        .big {
            color: red;
            text-align: center;
        }

        .container {
            color: green;
            text-align: right;
        }
    </style>
</head>
<body>
<h1 class="big">Hello, ${name}!</h1>
<p>Today is ${date}</p>
<input type="checkbox" <#if selected>checked="checked"</#if>>asd</input>
<div class="container">
    ${additionalHtml}
</div>
</body>
</html>