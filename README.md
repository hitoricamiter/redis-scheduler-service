# Dictionary Repository

Сборка и запуск:

1. Собираем JAR:
   mvn clean package -DskipTests

2. Запускаем Docker Compose:
   docker compose up --build

3. Применяем конфигурацию Kubernetes:
   kubectl apply -f deployment.yml
   kubectl apply -f service.yml
   kubectl apply -f ingress.yml

4. Портфорвард (если хотим работать локально через kubectl):
   kubectl port-forward deployment/myapp-deployment 8081:8080

5. Работа напрямую через Ingress Controller (NGINX):
   1. Получаем имя пода PostgreSQL:
      kubectl get pods
   2. Подключаемся к поду:
      kubectl exec -it <postgres-pod-name> -- bash
   3. Запускаем psql:
      psql -U postgres -d dictionary
   4. Создаем таблицы и добавляем стартовые данные:
      CREATE TABLE words (
          id SERIAL PRIMARY KEY,
          russian VARCHAR(255) UNIQUE,
          english VARCHAR(255)
      );

      INSERT INTO words (russian, english) VALUES
          ('кот', 'cat'),
          ('собака', 'dog'),
          ('дом', 'house');

      CREATE TABLE word_requests (
          id SERIAL PRIMARY KEY,
          russian VARCHAR(255),
          requested_at TIMESTAMP DEFAULT now()
      );

   Примечание: можно собрать кастомный образ PostgreSQL и использовать отдельный PVC, но для быстрого старта этого достаточно.

6. Доступ к приложению через Ingress:
   http://myapp.127.0.0.1.nip.io/dictionary/кот

В дальнейшем планируется добавление новых функций и эндпоинтов.
