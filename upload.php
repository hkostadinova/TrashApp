<?php

$user_name = "root";
$user_pass = "";
$host_name = "localhost";
$db_name = "melony";

$con = mysqli_connect($host_name, $user_name, $user_pass, $db_name);


if($con)
{
	
$image1 = $_POST['image1'];
$radio= $_POST['radio'];
$special= $_POST['special'];
    $name = $_POST['name'];
    $lat = (float) $_POST['lat'];
    $lng = (float) $_POST['lng'];


  
        $now = DateTime::createFromFormat('U.u', microtime(true));
        $id = $now->format('YmdHisu');
        $upload_path = "uploads/$id.jpeg";


        $sql = "insert into uploads(name,lat,lng,image1,image2,image3,image4,size,special) values 
                ('$name','$lat','$lng','$upload_path','','','','$radio','$special')ON DUPLICATE KEY UPDATE image2='$upload_path'";

      
	 if(mysqli_query($con,$sql))
        {
             file_put_contents($upload_path,base64_decode($image1));
			 
    
            echo json_encode(array('response'=>'Image Upload Successfully'));
			echo $radio;
        }
        else
        {
            echo json_encode(mysqli_error($con));    

          
        }
}
else
{
    echo json_encode(array('response'=>'Image Upload Failed2'));
}


mysqli_close($con);

?>