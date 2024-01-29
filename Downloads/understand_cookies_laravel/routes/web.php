<?php

use Illuminate\Support\Facades\Route;
use function Pest\Laravel\withHeaders;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider and all of them will
| be assigned to the "web" middleware group. Make something great!
|
*/

Route::get('/', function () {
    return view('welcome');
});

Route::get('/respone', function () {

    return redirect('/logout');
});


Route::get('/test',function() {
    $data = ["name"=>"saleh",  "age" => 27,  "job"=>"programmer"];
    $cookie = cookie("userInfo",serialize($data));
    return response()->view('test', ['userInfo' => ''])->withHeaders(["name"=>"saleh",  "age"=>27])->withCookie($cookie);
});

Route::get('/test/test', function() {
    $userInfo  = request()->cookie('userInfo');
    return view('test',['userInfo'=>unserialize($userInfo)]);
});