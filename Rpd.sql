-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1:3306
-- Время создания: Окт 26 2022 г., 17:40
-- Версия сервера: 5.7.39
-- Версия PHP: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `Rpd`
--

-- --------------------------------------------------------

--
-- Структура таблицы `account`
--

CREATE TABLE `account` (
  `id` bigint(20) NOT NULL,
  `active` bit(1) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `account`
--

INSERT INTO `account` (`id`, `active`, `email`, `password`, `username`) VALUES
(18, NULL, NULL, '$2a$08$gLATcXA.a4GGPLaDrQ69BuNAx0RxIRiAXPn5sB8rNGiyYjj/VPMi6', '111'),
(19, b'1', 'www.creeperpro@yandex.ru', '$2a$08$gLATcXA.a4GGPLaDrQ69BuNAx0RxIRiAXPn5sB8rNGiyYjj/VPMi6', 'Creeprus'),
(20, NULL, 'qwe@ba.ru', '$2a$08$gLATcXA.a4GGPLaDrQ69BuNAx0RxIRiAXPn5sB8rNGiyYjj/VPMi6', '123'),
(27, NULL, 'www.creeperpro@yandex.ru', '$2a$08$gLATcXA.a4GGPLaDrQ69BuNAx0RxIRiAXPn5sB8rNGiyYjj/VPMi6', 'boi'),
(28, b'1', '13131@ada.re', '$2a$08$lRk63602rxIItYu6l08jXOYgmPLQgoZYdzlMgN85bMOCbNSq9HTSC', '222'),
(35, b'1', 'test@test.test', '$2a$08$PFBLHENHUMnnHyPIeI16Ou9Fm6hQ.NeRkc2jrEk8vHIZMQq6bWsjy', 'test'),
(37, b'1', 'det@det.ru', '$2a$08$NX6TUJSL89qvfvXj/eQ4MOz6OeBPtT8EgDy0U5UeNqTHBkcfGrXvK', 'Moui'),
(38, b'1', 'exp@exp.ru', '$2a$08$.OJRAdM7oOGnPbty/b.7uO9FMaBglRhbJV2ZLn.VVF6RcNWn9xbEO', 'exp'),
(39, b'1', 'test@test.test', '$2a$08$aHxWw8mmxrGxuXoFh3olqOCdwbRfNI2BnqehM8DOUx8W48t8RWmBG', '000');

-- --------------------------------------------------------

--
-- Структура таблицы `adress`
--

CREATE TABLE `adress` (
  `id` bigint(20) NOT NULL,
  `date_of_arrival` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `adress`
--

INSERT INTO `adress` (`id`, `date_of_arrival`, `location`) VALUES
(29, '15.06.2002', 'ул Портная д 12'),
(30, '20.09.1995', 'ул Мемная д20');

-- --------------------------------------------------------

--
-- Структура таблицы `caseparticipator_crimecase`
--

CREATE TABLE `caseparticipator_crimecase` (
  `caseparticipator_id` bigint(20) NOT NULL,
  `crimecases_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `caseparticipator_crimecase`
--

INSERT INTO `caseparticipator_crimecase` (`caseparticipator_id`, `crimecases_id`) VALUES
(41, 37);

-- --------------------------------------------------------

--
-- Структура таблицы `case_participant_category`
--

CREATE TABLE `case_participant_category` (
  `id` bigint(20) NOT NULL,
  `category` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `case_participant_category`
--

INSERT INTO `case_participant_category` (`id`, `category`) VALUES
(33, 'Подозреваемый'),
(34, 'Соучастник'),
(36, 'Свидетель');

-- --------------------------------------------------------

--
-- Структура таблицы `case_participator`
--

CREATE TABLE `case_participator` (
  `id` bigint(20) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `surname` varchar(30) DEFAULT NULL,
  `case_participant_category_id` bigint(20) DEFAULT NULL,
  `patronymic` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `case_participator`
--

INSERT INTO `case_participator` (`id`, `name`, `surname`, `case_participant_category_id`, `patronymic`) VALUES
(41, 'SUS', 'SUSSY', 33, 'BAKA'),
(42, 'Imposter', 'Sussius', 33, 'Bakius'),
(44, 'oog', 'oof', 34, 'oof');

-- --------------------------------------------------------

--
-- Структура таблицы `clue`
--

CREATE TABLE `clue` (
  `id` bigint(20) NOT NULL,
  `date_of_find` varchar(30) DEFAULT NULL,
  `description` varchar(150) DEFAULT NULL,
  `location` varchar(150) DEFAULT NULL,
  `cases_id` bigint(20) DEFAULT NULL,
  `crime_case_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `clue`
--

INSERT INTO `clue` (`id`, `date_of_find`, `description`, `location`, `cases_id`, `crime_case_id`) VALUES
(50, '26.10.2023', 'Seen him vent.Sus', 'Space ship deck 12', NULL, 32),
(51, '26.01.2022', 'Найдены были следы... РЕЗНИ', 'Улица Пушкина до Колотушкина', NULL, 32);

-- --------------------------------------------------------

--
-- Структура таблицы `crime_case`
--

CREATE TABLE `crime_case` (
  `id` bigint(20) NOT NULL,
  `date_of_case` varchar(255) DEFAULT NULL,
  `description` varchar(150) DEFAULT NULL,
  `number_of_case` int(11) NOT NULL,
  `employee_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `crime_case`
--

INSERT INTO `crime_case` (`id`, `date_of_case`, `description`, `number_of_case`, `employee_id`) VALUES
(32, '25.10.2021', 'Украли сердечко', 1264, 20),
(37, '25.07.2020', 'Преступление против человечества', 228, 20);

-- --------------------------------------------------------

--
-- Структура таблицы `department`
--

CREATE TABLE `department` (
  `id` bigint(20) NOT NULL,
  `city` varchar(50) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL,
  `location` varchar(150) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `department`
--

INSERT INTO `department` (`id`, `city`, `country`, `location`) VALUES
(1, 'Skoop City', 'NUSA', 'ул. Пушкина, дом Колотушкино'),
(23, 'Tokyo', 'Japan', 'Невский проспект');

-- --------------------------------------------------------

--
-- Структура таблицы `dolj`
--

CREATE TABLE `dolj` (
  `id` bigint(20) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `salary` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `dolj`
--

INSERT INTO `dolj` (`id`, `name`, `salary`) VALUES
(6, 'Директор Участка', 200000),
(7, 'Детектив', 150000),
(8, 'Эксперт в области экспертизы', 355000),
(28, 'Devil Hunter', 30000);

-- --------------------------------------------------------

--
-- Структура таблицы `employee`
--

CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `patronymic` varchar(255) DEFAULT NULL,
  `surname` varchar(30) DEFAULT NULL,
  `account_id` bigint(20) DEFAULT NULL,
  `department_id` bigint(20) DEFAULT NULL,
  `dolj_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `employee`
--

INSERT INTO `employee` (`id`, `name`, `patronymic`, `surname`, `account_id`, `department_id`, `dolj_id`) VALUES
(20, 'Kobeniv', 'Dredgevich', 'Makima', 20, 1, 28),
(21, 'pain', 'Dredgevich', 'фвфв', 19, 1, 7),
(22, 'UgaUga', 'Noasd', 'Denin', 18, 1, 6),
(27, 'Dredge', 'Dredgevich', 'Dredgen', 27, 1, 8),
(31, 'demon', 'demon', 'demon', 28, NULL, NULL),
(40, 'Test', 'Test', 'Test', 35, 1, 28),
(46, 'Distortion', 'Moui', 'Detective', 37, NULL, NULL),
(52, 'experise', 'pepega', 'lord', 38, NULL, NULL),
(60, '???', '???', '???', 39, NULL, NULL);

-- --------------------------------------------------------

--
-- Структура таблицы `employee_adress`
--

CREATE TABLE `employee_adress` (
  `employee_id` bigint(20) NOT NULL,
  `adress_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `employee_adress`
--

INSERT INTO `employee_adress` (`employee_id`, `adress_id`) VALUES
(21, 29);

-- --------------------------------------------------------

--
-- Структура таблицы `expertise`
--

CREATE TABLE `expertise` (
  `id` bigint(20) NOT NULL,
  `date_of_expertise` varchar(30) DEFAULT NULL,
  `result` varchar(30) DEFAULT NULL,
  `clues_id` bigint(20) DEFAULT NULL,
  `expertise_tools_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `expertise`
--

INSERT INTO `expertise` (`id`, `date_of_expertise`, `result`, `clues_id`, `expertise_tools_id`) VALUES
(58, '26.10.2023', 'Была обнаружена... РЕЗНЯ', 51, 1),
(59, '25.10.2020', 'Предатель с нами...', 50, 1);

-- --------------------------------------------------------

--
-- Структура таблицы `expertisetools`
--

CREATE TABLE `expertisetools` (
  `id` bigint(20) NOT NULL,
  `expertisetool` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `expertisetools`
--

INSERT INTO `expertisetools` (`id`, `expertisetool`) VALUES
(1, 'Наугад'),
(55, 'На глаз');

-- --------------------------------------------------------

--
-- Структура таблицы `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(61);

-- --------------------------------------------------------

--
-- Структура таблицы `testimony`
--

CREATE TABLE `testimony` (
  `id` bigint(20) NOT NULL,
  `date_of_testimony` varchar(30) DEFAULT NULL,
  `case_participator_id` bigint(20) DEFAULT NULL,
  `testimony_desc` varchar(300) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `testimony`
--

INSERT INTO `testimony` (`id`, `date_of_testimony`, `case_participator_id`, `testimony_desc`) VALUES
(48, '26.10.2022', 41, 'Да не крал я ничего'),
(49, '27.10.2022', 44, 'РЕЗНЯ РЕЗНЯ РЕЗНЯ');

-- --------------------------------------------------------

--
-- Структура таблицы `user_role`
--

CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `user_role`
--

INSERT INTO `user_role` (`user_id`, `role`) VALUES
(1, 'ADMIN'),
(2, 'USER'),
(2, 'DETECTIVE'),
(3, 'USER'),
(3, 'HR'),
(6, 'USER'),
(7, 'USER'),
(7, 'OFFICER'),
(8, 'USER'),
(9, 'USER'),
(9, 'HR'),
(19, 'ADMIN'),
(28, 'USER'),
(28, 'OFFICER'),
(20, 'DETECTIVE'),
(36, 'USER'),
(36, 'DETECTIVE'),
(37, 'USER'),
(37, 'DETECTIVE'),
(38, 'USER'),
(38, 'EXPERTISE'),
(18, 'HR'),
(27, 'HR'),
(39, 'USER'),
(39, 'HR');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `adress`
--
ALTER TABLE `adress`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `caseparticipator_crimecase`
--
ALTER TABLE `caseparticipator_crimecase`
  ADD KEY `FKl0wwa1c0s0cmov83ixdxmvv07` (`crimecases_id`),
  ADD KEY `FKopnvsn3pm644dvkh71028upl3` (`caseparticipator_id`);

--
-- Индексы таблицы `case_participant_category`
--
ALTER TABLE `case_participant_category`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `case_participator`
--
ALTER TABLE `case_participator`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKcu354679yg1sihf2iodis7286` (`case_participant_category_id`);

--
-- Индексы таблицы `clue`
--
ALTER TABLE `clue`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKqmlq6as8mixqgcujsuah6leh6` (`crime_case_id`);

--
-- Индексы таблицы `crime_case`
--
ALTER TABLE `crime_case`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKnyr2jgx7dhaofwe5154gdqgrh` (`employee_id`);

--
-- Индексы таблицы `department`
--
ALTER TABLE `department`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `dolj`
--
ALTER TABLE `dolj`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKcfg6ajo8oske94exynxpf7tf9` (`account_id`),
  ADD KEY `FKbejtwvg9bxus2mffsm3swj3u9` (`department_id`),
  ADD KEY `FK30kexck42tly5hf3v80w1m0sa` (`dolj_id`);

--
-- Индексы таблицы `employee_adress`
--
ALTER TABLE `employee_adress`
  ADD KEY `FKfr57no7aaean3x7fmrfo96l2` (`adress_id`),
  ADD KEY `FKnn6n2x5ph7r7sgedety5gnjo` (`employee_id`);

--
-- Индексы таблицы `expertise`
--
ALTER TABLE `expertise`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKjnhl1eor7vfp4fvbk57ghg8ob` (`clues_id`),
  ADD KEY `FKgf9w516xxnmod7apopidivh6w` (`expertise_tools_id`);

--
-- Индексы таблицы `expertisetools`
--
ALTER TABLE `expertisetools`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `testimony`
--
ALTER TABLE `testimony`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKkff79laxfedx2jukxnrrf6s31` (`case_participator_id`);

--
-- Индексы таблицы `user_role`
--
ALTER TABLE `user_role`
  ADD KEY `FKlkhooy5w45r7bji6wv27a0wuq` (`user_id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `account`
--
ALTER TABLE `account`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `caseparticipator_crimecase`
--
ALTER TABLE `caseparticipator_crimecase`
  ADD CONSTRAINT `FKl0wwa1c0s0cmov83ixdxmvv07` FOREIGN KEY (`crimecases_id`) REFERENCES `crime_case` (`id`),
  ADD CONSTRAINT `FKopnvsn3pm644dvkh71028upl3` FOREIGN KEY (`caseparticipator_id`) REFERENCES `case_participator` (`id`);

--
-- Ограничения внешнего ключа таблицы `case_participator`
--
ALTER TABLE `case_participator`
  ADD CONSTRAINT `FKcu354679yg1sihf2iodis7286` FOREIGN KEY (`case_participant_category_id`) REFERENCES `case_participant_category` (`id`);

--
-- Ограничения внешнего ключа таблицы `clue`
--
ALTER TABLE `clue`
  ADD CONSTRAINT `FKqmlq6as8mixqgcujsuah6leh6` FOREIGN KEY (`crime_case_id`) REFERENCES `crime_case` (`id`);

--
-- Ограничения внешнего ключа таблицы `crime_case`
--
ALTER TABLE `crime_case`
  ADD CONSTRAINT `FKnyr2jgx7dhaofwe5154gdqgrh` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`);

--
-- Ограничения внешнего ключа таблицы `employee`
--
ALTER TABLE `employee`
  ADD CONSTRAINT `FK30kexck42tly5hf3v80w1m0sa` FOREIGN KEY (`dolj_id`) REFERENCES `dolj` (`id`),
  ADD CONSTRAINT `FKbejtwvg9bxus2mffsm3swj3u9` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`),
  ADD CONSTRAINT `FKcfg6ajo8oske94exynxpf7tf9` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`);

--
-- Ограничения внешнего ключа таблицы `employee_adress`
--
ALTER TABLE `employee_adress`
  ADD CONSTRAINT `FKfr57no7aaean3x7fmrfo96l2` FOREIGN KEY (`adress_id`) REFERENCES `adress` (`id`),
  ADD CONSTRAINT `FKnn6n2x5ph7r7sgedety5gnjo` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`);

--
-- Ограничения внешнего ключа таблицы `expertise`
--
ALTER TABLE `expertise`
  ADD CONSTRAINT `FKgf9w516xxnmod7apopidivh6w` FOREIGN KEY (`expertise_tools_id`) REFERENCES `expertisetools` (`id`),
  ADD CONSTRAINT `FKjnhl1eor7vfp4fvbk57ghg8ob` FOREIGN KEY (`clues_id`) REFERENCES `clue` (`id`);

--
-- Ограничения внешнего ключа таблицы `testimony`
--
ALTER TABLE `testimony`
  ADD CONSTRAINT `FKkff79laxfedx2jukxnrrf6s31` FOREIGN KEY (`case_participator_id`) REFERENCES `case_participator` (`id`);

--
-- Ограничения внешнего ключа таблицы `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `FKlkhooy5w45r7bji6wv27a0wuq` FOREIGN KEY (`user_id`) REFERENCES `account` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
