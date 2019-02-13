

let swap (a: int ref, b: int ref) =
   tmp :  int = !a;  (* deref *)
   


(* 
program SwapExample
  begin
    proc swap (a :  ref(int), b : ref(int)) {
      var tmp : int;
      tmp      := deref(a);
      deref(a) := deref(b);
      deref(b) := tmp;
  end;

  proc Main ( ) 
    begin
      var x : int;
      var y : int; 
      x := 42; y := 84;
      xr := ref (x); yr := ref(y);
      swap (xr,yr);
      // now, xr refs to  84 and yr to  42
    end;
  end;
 
*)
