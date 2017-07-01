<?php

$username = "root";
$host_name = "localhost";
$database = "melony";
$password = "";



// Start XML file, create parent node

$dom = new DOMDocument("1.0");
$node = $dom->createElement("markers");
$parnode = $dom->appendChild($node);

// Opens a connection to a MySQL server
//$db = new PDO('mysql:host=localhost;dbname=' . $database . ';charset=utf8mb4', 'root', '');
$connection = @mysqli_connect($host_name, $username, $password);
//$connection = $db;
if (!$connection) {
    die('Not connected : ' . mysqli_error());
}

// Set the active MySQL database

$db_selected = mysqli_select_db($connection, $database);
if (!$db_selected) {
    die(mysqli_error($connection));
}

// Select all the rows in the markers table

$query = "SELECT * FROM uploads WHERE 1";
$result = mysqli_query($connection, $query);
if (!$result) {
    die(mysqli_error($connection));
}

header("Content-type: text/xml");

// Iterate through the rows, adding XML nodes for each

while ($row = @mysqli_fetch_assoc($result)) {
    // Add to XML document node
    $node = $dom->createElement("marker");
    $newnode = $parnode->appendChild($node);
    $newnode->setAttribute("description", $row['description']);
    $newnode->setAttribute("name", $row['name']);
    $newnode->setAttribute("image1", $row['image1']);
    $newnode->setAttribute("image2", $row['image2']);
    $newnode->setAttribute("lat", $row['lat']);
    $newnode->setAttribute("lng", $row['lng']);
}

echo $dom->saveXML();
?>