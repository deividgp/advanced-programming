import { createServer } from 'http'
import { Server } from 'socket.io'

const httpServer = createServer()
const io = new Server(httpServer, {
  cors: {
    origin: '*'
  }
})

io.on('connection', (socket) => {
  socket.on('create_actor', (data) => {
    socket.broadcast.emit('create_actor', data)
  })

  socket.on('send_message', (data) => {
    socket.broadcast.emit('send_message', data)
  })

  socket.on('send_actors', (data) => {
    socket.broadcast.emit('send_actors', data)
  })

  socket.on('get_actors', (data) => {
    socket.broadcast.emit('get_actors', data)
  })

  socket.on('new_actor', (data) => {
    socket.broadcast.emit('new_actor', data)
  })

  socket.on('update_status', (data) => {
    socket.broadcast.emit('update_status', data)
  })
})

httpServer.listen(3000)
