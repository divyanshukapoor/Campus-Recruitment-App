<?php
foreach ($_POST as $key => $value) { 
    $_POST[$key] = mysql_real_escape_string($value); 
  }
$a=$_POST['name'];
$b=$_POST['password'];
$c=$_POST['email'];
$d=$_POST['reg_no'];
$e=$_POST['contact'];
$f=$_POST['encoded_image'];
$g=$_POST['branch'];
$h=$_POST['cpi'];

$z="true";
$v=0;
$t=10;
$p=0;
if(isset($_POST['encoded_image']) ){
if($f!="not"){
$decode_string=base64_decode($f);
file_put_contents("image/".$d.".JPG",$decode_string);
}
}
$con=mysql_connect("localhost","root","");
mysql_select_db('temp');
$query="SELECT * FROM intern WHERE Name='$a' AND Email='$c'";
$result=mysql_query($query);
$count=mysql_num_rows($result);
if($count==0){
$query1="INSERT INTO intern(Name,Password,Email,Registration,Contact,Image,Eligible,Branch,cpi)VALUES ('$a','$b','$c','$d','$e','$d','$z','$g','$h')";
mysql_query($query1);
echo "Registration Success...";
}
else{
echo "User exists";
}
mysql_close($con);
?>