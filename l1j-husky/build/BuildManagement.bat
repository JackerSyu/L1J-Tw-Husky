@ECHO OFF
TITLE Lineage Java Server Build Management
SETLOCAL ENABLEDELAYEDEXPANSION

REM =================================== ���|�]�w =====================================
CD %~p0
CD ..

REM ================================================== �]�w�Ѽ� ==================================================
SET ANT_BINARY=build\ant\bin\ant
SET BASE_PATH=..

REM ================================================== ��ܥ\��ﶵ ==================================================
MODE CON COLS=80 LINES=26
:init
SET ARGS=
CLS
ECHO ============================== L1J �ظm�޲z�t�� ==============================
ECHO.                                                                 Design by Neo
ECHO.
ECHO �п�ܭn�ϥΪ��\��G
ECHO.
ECHO  1. �@��ظm
ECHO     ���㪺���~�T����T, �}�o�ɸ�����X���~��].
ECHO.
ECHO  2. �̤p�ظm (�L������T)
ECHO     �̤p�ƽsĶ�᪺�֤ߵ{�Ǥj�p, �K������ǿ�.
ECHO.
ECHO.
ECHO.
ECHO.
ECHO.
ECHO.
ECHO.
ECHO.
ECHO.
ECHO.
ECHO.
ECHO.
ECHO. 0. �������{��
ECHO.
ECHO.
SET /P ARGS=�п�J�s���ӿ�ܥ\��G 
CLS
IF "%ARGS%"=="" GOTO ERR_NO_ARGS
IF %ARGS%==1 GOTO opt1
IF %ARGS%==2 GOTO opt2
IF %ARGS%==0 GOTO exit
GOTO ERR_NO_ARGS

REM ================================================== �\��ﶵ ==================================================
:opt1
START %ANT_BINARY% all
GOTO init

:opt2
START %ANT_BINARY% mini
GOTO init

REM ================================================== ���~�B�z ==================================================
:ERR_NO_ARGS
CLS
ECHO �S���o�ӿﶵ %ARGS%
ECHO.
PAUSE
GOTO init

REM ================================================== �����{�� ==================================================
:exit
EXIT