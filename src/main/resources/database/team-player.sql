INSERT INTO type(type_id, created_at, created_by, updated_at, updated_by, code, entity_name, is_actv, name) VALUES(1, null, null, null, null, 'basket', 'TeamEntity', 1, 'Basketball Team');
INSERT INTO type(type_id, created_at, created_by, updated_at, updated_by, code, entity_name, is_actv, name) VALUES(2, null, null, null, null, 'football', 'TeamEntity', 1, 'Football Team');

INSERT INTO team(team_id, created_at, created_by, updated_at, updated_by, available_cap, is_actv, max_cap, team_name, type_id) VALUES(1, null, null, null, null, 3, 1, 5, 'Los Angeles Lakers', 1);
INSERT INTO team(team_id, created_at, created_by, updated_at, updated_by, available_cap, is_actv, max_cap, team_name, type_id) VALUES(2, null, null, null, null, 1, 1, 5, 'Chicago Bulls', 1);
INSERT INTO team(team_id, created_at, created_by, updated_at, updated_by, available_cap, is_actv, max_cap, team_name, type_id) VALUES(3, null, null, null, null, 1, 1, 5, 'Philadelphia 76ers', 1);
INSERT INTO team(team_id, created_at, created_by, updated_at, updated_by, available_cap, is_actv, max_cap, team_name, type_id) VALUES(4, null, null, null, null, 1, 1, 5, 'Boston Celtics', 1);

INSERT INTO `position`(position_id, created_at, created_by, updated_at, updated_by, code, is_actv, name, type_id) VALUES(1, null, null, null, null, 'PG', 1, 'Point guard', 1);
INSERT INTO `position`(position_id, created_at, created_by, updated_at, updated_by, code, is_actv, name, type_id) VALUES(2, null, null, null, null, 'SG', 1, 'Shooting guard', 1);
INSERT INTO `position`(position_id, created_at, created_by, updated_at, updated_by, code, is_actv, name, type_id) VALUES(3, null, null, null, null, 'SF', 1, 'Small forward', 1);
INSERT INTO `position`(position_id, created_at, created_by, updated_at, updated_by, code, is_actv, name, type_id) VALUES(4, null, null, null, null, 'PW', 1, 'Power forward', 1);
INSERT INTO `position`(position_id, created_at, created_by, updated_at, updated_by, code, is_actv, name, type_id) VALUES(5, null, null, null, null, 'C', 1, 'Center', 1);

INSERT INTO player(player_id, created_at, created_by, updated_at, updated_by, is_actv, name, position_id, surname, team_id) VALUES(1, null, null, null, null, 1, 'LeBron James', 1, 'Raymone', 1);
INSERT INTO player(player_id, created_at, created_by, updated_at, updated_by, is_actv, name, position_id, surname, team_id) VALUES(2, null, null, null, null, 1, 'Name', 1, 'Surname', 1);
INSERT INTO player(player_id, created_at, created_by, updated_at, updated_by, is_actv, name, position_id, surname, team_id) VALUES(3, null, null, null, null, 1, 'Name2', 1, 'Surname2', 1);
