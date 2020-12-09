<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
    <title>tasty-seeker-food</title>
    <script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=sesxh1guj9"></script>
</head>
<body>
<div>23234433434222</div>
<div>${customMessage}</div>
<div id="map" style="width:500px;height:600px;"></div>

<script>
    var mapOptions = {
        center: new naver.maps.LatLng(37.3595704, 127.105399),
        zoom: 10
    };

    var map = new naver.maps.Map('map', mapOptions);
</script>
</body>
</html>