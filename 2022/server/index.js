import { createServer } from "http";
import { Server } from "socket.io";

const httpServer = createServer();
const io = new Server(httpServer);

io.on("connection", (socket) => {
  socket.on("data", (data) => {
    socket.broadcast.emit("get_data_" + socket.app, data);
  });

  socket.on("create_actor", (data) => {
    socket.broadcast.emit("create_actor", data);
  });

  socket.on("send_message", (data) => {
    socket.broadcast.emit("send_message", data);
  });
});

httpServer.listen(3000);