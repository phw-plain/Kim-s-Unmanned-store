import React, { useState } from 'react';

export const Main = () => {
  const [todo, setTodo] = useState("");

  const onChange = (e) => {
    setTodo(e.target.value);
  }

  return (
    <form>
      <input className="todo_input" type="text" value={todo} name="todo" onChange={onChange} required />
      <button type="submit" className="submit_btn">ENTER</button>
    </form>
  )
}