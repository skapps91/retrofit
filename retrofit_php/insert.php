<?php

include 'global-connect.php';

$response = array();

if( isset( $_POST['name'] ) ){

    $Name = $_POST['name'];
    $Age = $_POST['age'];


    $query = "INSERT INTO student ( `name`, `age` ) VALUES ( '$Name', '$Age' )";


    if(mysql_query( $query )){

        $response['code']=1;

        echo(json_encode($response));

    }else{
        $response['code']=0;

        echo(json_encode($response));

    }



}


?>