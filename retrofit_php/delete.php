<?php

include 'global-connect.php';

$response = array();

if( isset( $_POST['id'] ) ){

    $Id = $_POST['id'];


    $query=mysql_query("DELETE FROM student WHERE id = '$Id' ");

    if( $query ){

        $response['code']=1;

        echo(json_encode($response));

    }else{
        $response['code']=0;

        echo(json_encode($response));

    }



}


?>