resource "aws_vpc" "quattys_vpc" {
  cidr_block           = "10.0.0.0/16"
  enable_dns_hostnames = true
  enable_dns_support   = true

  tags = {
    Name = "quattys_vpc_1"
  }
}

resource "aws_subnet" "quattys_subnet" {
  vpc_id     = aws_vpc.quattys_vpc.id
  cidr_block = "10.0.1.0/24"
  availability_zone = "us-east-1a"
  map_public_ip_on_launch = true

  tags = {
    Name = "quattys_subnet_pub"
  }
}

resource "aws_internet_gateway" "quattys_igw" {
  vpc_id = aws_vpc.quattys_vpc.id

  tags = {
    Name = "quattys_igw"
  }
}

resource "aws_route_table" "quattys_rtb" {
  vpc_id = aws_vpc.quattys_vpc.id

  tags = {
    Name = "quattys_rtb"
  }
}

resource "aws_route" "quattys_route" {
  route_table_id = aws_route_table.quattys_rtb.id
  destination_cidr_block = "0.0.0.0/0"
  gateway_id = aws_internet_gateway.quattys_igw.id
}

resource "aws_route_table_association" "aws_rtb_ass" {
  route_table_id = aws_route_table.quattys_rtb.id
  subnet_id = aws_subnet.quattys_subnet.id
}

resource "aws_instance" "quattys_ec2" {
  instance_type = "t2.micro"
  key_name = aws_key_pair.quattys_key.id
  vpc_security_group_ids = [aws_security_group.quattys_sg.id]
  subnet_id = aws_subnet.quattys_subnet.id
  ami = data.aws_ami.quattys_ami.id
  user_data = file("userdata.tpl")

  root_block_device {
    volume_size = 8
  }

  tags = {
    Name = "quattys_ec2"
  }
}