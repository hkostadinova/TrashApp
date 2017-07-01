<?php

// array for JSON response
$response = array();

// include db connect class
require_once __DIR__ . '/db_connect.php';

// connecting to db
$db = new DB_CONNECT();

// get all trashes from Trash table
$result = mysqli_query($db->connect(), "SELECT * FROM Trash") or die(mysqli_error($db->connect()));

if ($result->num_rows >0) {
 
 
 while($row[] = $result->fetch_assoc()) {
 
 $tem = $row;
 
 $json = json_encode($tem);
 
 
 }
 
} else {
 echo "No Results Found.";
}
 echo $json;
$db->close();
?>