# MarcoProdCRUDWithJWT

Docker  commmand to build the image

  docker build -t marco/test .
  
Docker command to Run the container

  docker run -p 8081:8081 marco/test
  
  Room for improvement
  
  Currently the file is stored on the file System and path stored in the DB.As partof improvment can be stored in Bucket in a Cloud envrironment
