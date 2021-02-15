# MarcoProdCRUDWithJWT

  - Docker  commmand to build the image.
  
        docker build -t marco/test .
  
  - Docker  commmand to build the image.
  
        docker run -p 8081:8081 marco/test
  
  
 ###  Room for improvement
  
   - Currently the file is stored on the file System and path stored in the DB.
   - As partof improvment can be stored in Bucket in a Cloud envrironment
   - Deflate before storing and inlfate while reading
   - Currently on Load of the page ,the image is not dispalyed ,so user can update the image
   
   ![image](https://user-images.githubusercontent.com/22238550/107945196-93772e80-6f8f-11eb-87bf-681e90151195.png)
   
   ![image](https://user-images.githubusercontent.com/22238550/107945318-bc97bf00-6f8f-11eb-85b0-f620b6ff5e0e.png)
   
 #### Notes
 
   - Angular dist folder is copied to static folder
