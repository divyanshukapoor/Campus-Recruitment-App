<?php
foreach ($_POST as $key => $value) { 
    $_POST[$key] = mysql_real_escape_string($value); 
  }
$x=$_POST['reg_no'];

$con=mysql_connect("localhost","root","");
mysql_select_db('temp');

if(empty($x))
{
echo "please fill all the details";
}
else
{
$query="SELECT * FROM intern WHERE Registration='$x' ";
$result=mysql_query($query);
$count=mysql_num_rows($result);
if($count==0){
echo "Login failed...Try again";
}
else
{
echo "Login Success...".PHP_EOL;
$ans=mysql_query("SELECT Password FROM intern WHERE Registration='$x' ");
$row=mysql_fetch_assoc($ans);
echo $row['Password'].PHP_EOL;
}
}
mysql_close($con);
?>