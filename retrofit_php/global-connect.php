<?php 

$host='localhost';
$uname='root';
$pwd='';
$db="android_test";


$timezone = "Asia/Karachi"; 
if(function_exists('date_default_timezone_set')){ 
    date_default_timezone_set($timezone);
}


$response = array();

$con = @mysql_connect($host,$uname,$pwd) or die("connection failed");
mysql_select_db($db,$con) or die("db selection failed");

error_reporting(0);
ini_set('display_errors', 0);

?>