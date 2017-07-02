<?php

// array for JSON response
$response = array();

// check for required fields
if (isset($_POST['Name']) && isset($_POST['Description'])) {

    $Trash_Name = $_POST['Name'];
    $Latitude= floatval($_POST['Latitude']);
    $Longitude= floatval($_POST['Longitude']);
    $Trash_Description = $_POST['Description'];
    $IsCleaned = $_POST['IsCleaned'];
    $Trash_Size = intval($_POST['Size_idSize']);

    // include db connect class
    require_once __DIR__ . '/db_connect.php';

    // connecting to db
    $db = new DB_CONNECT();

    // mysql inserting a new row
    $sql = "INSERT INTO Trash (Name, Latitude, Longitude, Description, IsCleaned, Size_idSize) 
VALUES ('$Trash_Name', '$Latitude', '$Longitude', '$Trash_Description', '$IsCleaned ', '$Trash_Size ')";
    $result = mysqli_query($db->connect(), $sql);

    // check if row inserted or not
    if ($result) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "Trash successfully created.";

        // echoing JSON response
        echo json_encode($response["message"]);
    } else {
        // failed to insert row
        $response["success"] = 0;
        $response["message"] = $sql;

        // echoing JSON response
        echo json_encode($response["message"]);
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";

    // echoing JSON response
    echo json_encode($response);
}