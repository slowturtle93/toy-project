나만을 위한 toy project

* minikube를 이용하여 kubernetes 환경 구축

kubernetes context switching 방법
- kubectl config get-contexts (현재 kubernetes 연결 정보 확인)
- kubectl config use-context minikube (kubernetes 연결 정보 스위칭)

minikube를 이용하여 kubernetes 환경 구축(docker, docker-compose 없다면 install 필요)
 - minikube install
   - curl -LO https://storage.googleapis.com/minikube/releases/latest/minikube-darwin-arm64
   - chmod +x minikube-darwin-arm64
   - sudo mv minikube-darwin-arm64 /usr/local/bin/minikube
 - minikube 실행 시 docker driver로 
   - minikube docker-env
   - eval $(minikube -p minikube docker-env)
   - minikube start --driver=docker(driver 부분은 생략 가능)
   - minikube가 실행이 되고 난 후 docker image와 kubernetes 실행
   - kubectl get nodes 명령어를 통해 minikube node 확인
 - docker image build 및 kubernetes pod, deployment, service 실행
   - docker build -f ./Dockerfile -t {image-name}:{tag} .
   - kubectl apply -f deployment.yaml
   - minikube service {service-name} --url 명령어를 통해 접근 {ip}:{port} 확인
 - (주의) kubernetes service 실행 시 nodePort로의 접근 불가
   - 호스트에서 bridge 네트워크 인터페이스가 존재하지 않기 때문에 컨테이너에 IP로는 접근을 못하고 포트 포워딩을 통한 터널링으로 접근을 할 수 있다
     - https://code1212-uh.tistory.com/19
   - minikube service toy-app-svc --url 명령어를 통해 minikube로 접근가능한 {ip}:{port}로 접근


kubectl 명령어
- kubectl scale deployment/{deployment-name} --replicas=0 (pod scale 조정)
- kubectl autoscale deployment/{deployment-name} --min=2 --max=10 --cpu-percent=80 (pod auto scale)
- kubectl delete deployment {deployment-name} -n {name-space} (deplyment, pod 삭제)
- kubectl describe pod {pod-name} (pod 상세 정보 확인)

========================================================================
