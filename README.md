# dictionary-repository

1. Собираем jar: mvn clean package -DskipTests
2. Делаем docker compose up --build
3. Кормим наш кубер прописанной конфигурацией
	I. kubectl apply -f deployment.yml
	II. kubectl apply -f service.yml
	III. kubectl apply -f ingress.yml
4. Делаем портфорвард: kubectl port-forward deployment/myapp-deployment 8081:8080
5. Если не хотим делать портфорвард и хотим поработать напрямую через Ingress Controlelr (NGINX), проделываем небольшое и простое количество действий:


	1. kubectl get pods - узнаем имя пода нашего постгресса
	2. kubectl exec -it postgres-0 -- bash - непосредственно подключаемся
	3. psql -U postgres -d dictionary - запускаем psql
	4. 
	```
	CREATE TABLE words (
    id SERIAL PRIMARY KEY,
    russian VARCHAR(255) UNIQUE,
    english VARCHAR(255)
);

INSERT INTO words (russian, english) VALUES ('кот', 'cat'), ('собака', 'dog'), ('дом', 'house');

CREATE TABLE word_requests (
    id SERIAL PRIMARY KEY,
    russian VARCHAR(255),
    requested_at TIMESTAMP DEFAULT now()
);
```

и непосредственно вставляем стартовый инит, можно конечно собрать кастомный postgres  и испоьзовать отдельный PVC, но так как я указал мне было удобнее

http://myapp.127.0.0.1.nip.io/dictionary/кот - по этому адрессу все будет работать, в последующем дополню другими фичами