export IMAGES=`docker images | grep \<none\> | awk '{print $3}'`
 
for image in $IMAGES
do
   docker rmi -f $image
done

