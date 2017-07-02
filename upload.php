<?php

$user_name = "root";
$user_pass = "";
$host_name = "localhost";
$db_name = "melony";

$con = mysqli_connect($host_name, $user_name, $user_pass, $db_name);


if($con)
{
	
$image1 = $_POST['image1'];
$image2 = $_POST['image2'];
$image3 = $_POST['image3'];
$image4 = $_POST['image4'];
$radio= $_POST['radio'];
$special= $_POST['special'];
    $name = $_POST['name'];
    $lat = (float) $_POST['lat'];
    $lng = (float) $_POST['lng'];


  
        $now = DateTime::createFromFormat('U.u', microtime(true));
        $id = $now->format('YmdHisu');
        $upload_path = "uploads/$id.jpeg";
		     $upload_path2 = "uploads/2.jpeg";
			 		     $upload_path3 = "uploads/3.jpeg";
						 		     $upload_path4 = "uploads/4.jpeg";


        $sql = "insert into uploads(name,lat,lng,image1,image2,image3,image4,size,special) values 
                ('$name','$lat','$lng','$upload_path','$upload_path2','$upload_path3','$upload_path4','$radio','$special')";

      
	 if(mysqli_query($con,$sql))
        {
             file_put_contents($upload_path,base64_decode($image1));
			 file_put_contents($upload_path2,base64_decode($image2));
			 file_put_contents($upload_path3,base64_decode($image3));
			 file_put_contents($upload_path4,base64_decode($image4));
		
    
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