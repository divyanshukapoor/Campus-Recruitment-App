<?php
foreach ($_POST as $key => $value) { 
    $_POST[$key] = mysql_real_escape_string($value); 
  }
$a=$_POST['reg_no'];
$b=$_POST['b10'];
$c=$_POST['s10'];
$d=$_POST['y10'];
$e=$_POST['p10'];
$f=$_POST['b12'];
$g=$_POST['s12'];
$h=$_POST['y12'];
$i=$_POST['p12'];
$j=$_POST['s1'];
$k=$_POST['s2'];
$l=$_POST['s3'];
$m=$_POST['s4'];
$n=$_POST['s5'];
$o=$_POST['s6'];
$p=$_POST['s7'];
$q=$_POST['s8'];
$tr="1";
$con=mysql_connect("localhost","root","");
mysql_select_db('temp');
$query="SELECT * FROM intern WHERE Registration='$a' ";
$result=mysql_query($query);
$count=mysql_num_rows($result);
if($count>0){
$query1="UPDATE intern SET 10Board='$b',10School='$c',10Year='$d',10Persentage='$e',12Board='$f',12School='$g',12Year='$h',12Persentage='$i',1Sem='$j',2Sem='$k',3Sem='$l',4Sem='$m',5Sem='$n',6Sem='$o',7Sem='$p',8Sem='$q' ,stateAcademic='$tr' WHERE Registration='$a' ";
mysql_query($query1);
echo "Succesfully submitted...";
}
mysql_close($con);
?>