<?php
 
// array for JSON response
$response = array();
 
// check for required fields
if (isset($_POST['idTrash'])) {
    $idTrash = $_POST['idTrash'];
 
    // include db connect class
    require_once __DIR__ . '/db_connect.php';
 
    // connecting to db
    $db = new DB_CONNECT();
 
    // mysql update row with matched pid
    $result = mysqli_query($db->connect(), "DELETE FROM Trash WHERE idTrash = $idTrash");
 
    // check if row deleted or not
    if (mysqli_affected_rows($db->connect()) > 0) {
        // successfully updated
        $response["success"] = 1;
        $response["message"] = "Trash successfully deleted";
 
        // echoing JSON response
        echo json_encode($response);
    } else {
        // no trash found
        $response["success"] = 0;
        $response["message"] = "No trash found";
 
        // echo no trashes JSON
        echo json_encode($response);
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";
 
    // echoing JSON response
    echo json_encode($response);
}

