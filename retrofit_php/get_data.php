<?php

include 'global-connect.php';

$response = array();
$response['values'] = array();

if( isset( $_GET['id'] ) ){

    $Id = $_GET['id'];

    $query = mysql_query("SELECT * FROM student WHERE id = '$Id' ");

    if( $query ){

        while($row = mysql_fetch_array($query)){

            $value['id'] = $row["id"];
            $value['name'] = $row["name"];
            $value['age'] = $row["age"];

            array_push($response['values'], $value);

        }

        $response['code']=1;

        echo(json_encode($response));

    } else{
        $response['code']=0;

        echo(json_encode($response));

    }



}


?>