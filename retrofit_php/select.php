<?php

include 'global-connect.php';

$response = array();

if( isset( $_POST['data'] ) ){


    $query = "SELECT * FROM student";


    $response['values'] = array();

    $q1 = mysql_query( $query );

    if( $q1 ){

        while($row = mysql_fetch_array($q1)){

            $value['id'] = $row["id"];
            $value['name'] = $row["name"];
            $value['age'] = $row["age"];

            array_push($response['values'], $value);

        }

        $response['code']=1;
        echo(json_encode($response));


    }else{
        $response['code']=0;

        echo(json_encode($response));

    }





}


?>