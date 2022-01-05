kubectl delete -f redis.yaml
kubectl delete -f tenant-service.yaml

kubectl delete -f dblaboratory.yaml
kubectl delete -f laboratory-service.yaml

kubectl delete -f dbinventory.yaml
kubectl delete -f inventory-service.yaml

kubectl delete -f gateway-service.yaml
