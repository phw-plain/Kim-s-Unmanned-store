"use strict";

const User = require("../../models/User");

const output ={
  home : (req, res) => {
    res.render("home/Home");
  },
  products : (req, res) => {
    res.render("home/Products");
  },
  buy: (req,res)=>{
    res.render("home/Buy");
  },
  permute: (req,res)=>{
    res.render("home/Permute");
  }
  };

const process={
    buy:async (req, res)=>{
    const user = new User(req.body);
    const response = await user.buy();
    return res.json(response);
  },
    permute:async (req, res)=>{
    const user = new User(req.body);
    const response = await user.buy();
    return res.json(response);
  }
};

module.exports = {
  output,
  process,
};