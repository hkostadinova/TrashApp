<?php

// array for JSON response

// include db connect class
require_once __DIR__ . '/db_connect.php';

// connecting to db
$db = new DB_CONNECT();

// check for post data

if($_SERVER['REQUEST_METHOD']=='POST') {
    $idTrash = $_POST['idTrash'];
    //$idTrash = 18;

    // get a trash from trashes table
    $sql = "SELECT * FROM Trash WHERE idTrash = $idTrash";
    $result = $db->connect()->query($sql);

    //$result = mysqli_query($db->connect(), "SELECT * FROM Trash WHERE idTrash = $idTrash");

    if (!empty($result)) {
        // check for empty result
        if (mysqli_num_rows($result) > 0) {

            while($row[] = $result->fetch_assoc()) {
            $tem = $row;
            $json = json_encode($tem);
            }
 
        } else {
            echo "No Results Found.";
        }
        echo $json;
   } 
}
$db->close();

