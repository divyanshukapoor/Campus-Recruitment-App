<?php
foreach ($_POST as $key => $value) { 
    $_POST[$key] = mysql_real_escape_string($value); 
  }
$a=$_POST['name'];
$b=$_POST['father'];
$c=$_POST['mother'];
$d=$_POST['email'];
$e=$_POST['address'];
$tr="1";
$con=mysql_connect("localhost","root","");
mysql_select_db('temp');
$query="SELECT * FROM intern WHERE Name='$a' AND Email='$d' ";
$result=mysql_query($query);
$count=mysql_num_rows($result);
if($count>0){
$query1="UPDATE intern SET Father='$b',Mother='$c',Address='$e',statePersonal='$tr' WHERE Name='$a' AND Email='$d' ";
mysql_query($query1);
echo "Succesfully submitted...";
}
mysql_close($con);
?>