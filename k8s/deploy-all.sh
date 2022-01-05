kubectl create -f redis.yaml
kubectl create -f tenant-service.yaml

kubectl create -f dblaboratory.yaml
kubectl create -f laboratory-service.yaml

kubectl create -f dbinventory.yaml
kubectl create -f inventory-service.yaml

kubectl create -f gateway-service.yaml
