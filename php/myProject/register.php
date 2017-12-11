<?php
foreach ($_POST as $key => $value) { 
    $_POST[$key] = mysql_real_escape_string($value); 
  }
$x=$_POST['name'];
$y=$_POST['password'];
$z=$_POST['email'];
$encoded_image=$_POST['encoded_image'];
//$encoded_pdf=$_POST['encoded_pdf'];
$decode_string=base64_decode($encoded_image);
file_put_contents("image/".$x.".JPG",$decode_string);

//$decode_stringpdf=base64_decode($encoded_pdf);
//file_put_contents("resume/".$z.".pdf",$decode_stringpdf);

mysql_connect("localhost","root","");
mysql_select_db('temp');

if(empty($x)||empty($y)||empty($z)||empty($encoded_image))
{
//echo "please fill all the details";
}
else
{
$query="SELECT * FROM one WHERE Name='$x' AND Password='$y' AND Email='$z'";
$result=mysql_query($query);
$count=mysql_num_rows($result);
if($count==0){

$query1="INSERT INTO one(Name,Password,Email,Image)VALUES ('$x','$y','$z','$encoded_image')";
mysql_query($query1);
//echo "Database created";
}
else
{
//echo "user exists";
}
}
?>