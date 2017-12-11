<?php
  
    $file_path = "resume/";
     
    $file_path = $file_path . basename( $_FILES['uploaded_file']['name']);
    //  $file_path=  $file_path . "11.pdf";
    if(move_uploaded_file($_FILES['uploaded_file']['tmp_name'], $file_path) ){
        echo "success";
    } else{
        echo "fail";
    }
 ?>