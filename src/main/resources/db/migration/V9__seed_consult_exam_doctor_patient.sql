insert into doctor (name, specialty)
values
('Dra. Ana Souza', 'Cardiologia'),
('Dr. João Pereira', 'Ortopedia'),
('Dra. Marina Costa', 'Dermatologia'),
('Dr. Carlos Almeida', 'Clínico Geral');


insert into exam (name, description, price)
values
('Hemograma Completo', 'Exame laboratorial para análise de sangue.', 120.00),
('Raio-X de Tórax', 'Imagem radiológica do tórax para avaliação pulmonar.', 200.00),
('Ultrassom Abdominal', 'Exame de imagem para avaliação dos órgãos abdominais.', 350.00),
('Eletrocardiograma', 'Registro da atividade elétrica do coração.', 180.00);

insert into patient (name, email, birth_date, phone)
values
('Maria Silva', 'maria.silva@email.com', '1990-05-14', '(11) 98877-1122'),
('João Santos', 'joao.santos@email.com', '1985-09-22', '(21) 97766-2233'),
('Carla Oliveira', 'carla.oliveira@email.com', '1992-03-10', '(31) 98855-3344'),
('Pedro Rocha', 'pedro.rocha@email.com', '1988-07-30', '(41) 97744-5566');

insert into consult (patient_id, doctor_id, exam_id, consult_date, notes)
values
(1, 1, 1, '2025-01-10 09:30:00', 'Consulta de rotina com exames preventivos.'),
(2, 2, 2, '2025-02-05 14:00:00', 'Consulta de retorno para avaliação dos resultados dos exames.'),
(3, 1, 3, '2025-03-20 11:15:00', 'Paciente apresentou sintomas leves, recomendado acompanhamento.'),
(1, 3, 4, '2025-04-18 10:45:00', 'Solicitado novo exame de imagem para confirmação diagnóstica.');






