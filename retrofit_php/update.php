<?php

include 'global-connect.php';

$response = array();

if( isset( $_POST['name'] ) ){

    $Id = $_POST['id'];
    $Name = $_POST['name'];
    $Age = $_POST['age'];


    $query=mysql_query("UPDATE student SET name = '$Name', age = '$Age' WHERE id = '$Id' ");

    if( $query ){

        $response['code']=1;

        echo(json_encode($response));

    }else{
        $response['code']=0;

        echo(json_encode($response));

    }



}


?>