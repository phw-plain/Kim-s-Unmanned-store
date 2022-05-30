"use strict";

const express = require("express");
const router = express.Router();

const ctrl = require("./home.ctrl");

router.get("/", ctrl.output.home);
router.get("/products", ctrl.output.products);
router.get("/buy", ctrl.output.buy);
router.get("/permute", ctrl.output.permute);
router.post("/buy", ctrl.process.buy);
router.post("/permute", ctrl.process.permute);
module.exports = router;