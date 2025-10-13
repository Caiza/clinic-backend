-- ============================================
-- Adicionando constraints e índices otimizados
-- ============================================

-- 🔹 Garantir unicidade de e-mail do paciente
alter table patient
add CONSTRAINT uq_patient_email UNIQUE (email);

-- 🔹 Garantir unicidade de nome do exame
alter table exam
add CONSTRAINT uq_exam_name UNIQUE (name);

-- 🔹 Garantir unicidade de username e email do usuário
alter table users
add CONSTRAINT uq_user_username UNIQUE (username),
ADD CONSTRAINT uq_user_email UNIQUE (email);

-- 🔹 Adicionar índices para acelerar buscas frequentes
create index idx_doctor_specialty on doctor (specialty);
create index idx_patient_name on patient (name);
create index idx_exam_price on exam (price);

-- 🔹 Adicionar foreign keys na tabela consult
alter table consult
add CONSTRAINT fk_consult_patient FOREIGN KEY (patient_id) REFERENCES patient(id) ON delete CASCADE,
ADD CONSTRAINT fk_consult_doctor FOREIGN KEY (doctor_id) REFERENCES doctor(id) ON delete CASCADE,
ADD CONSTRAINT fk_consult_exam FOREIGN KEY (exam_id) REFERENCES exam(id) ON delete SET NULL;

-- 🔹 Criar uma chave composta para evitar duplicidade de agendamento
alter table consult
add CONSTRAINT uq_consult_unique UNIQUE (patient_id, doctor_id, consult_date);

-- 🔹 Índice para acelerar consultas por data
create index idx_consult_date on consult (consult_date);
