from socketserver import *
from win10toast import ToastNotifier



class RequestHandler(BaseRequestHandler):
    def handle(self):
        self.data = self.request.recv(1024).strip()
        print(self.data)

        if self.data.decode() == "WAKE":
            self.request.sendall(self.data)
            toast()


def toast():
    toaster = ToastNotifier()
    toaster.show_toast("PyPing", "You've been pinged!", duration=5)


if __name__ == "__main__":
    HOST, PORT = "XXX.XXX.XXX.XXX", XXXX

    with TCPServer((HOST, PORT), RequestHandler) as server:
        server.serve_forever()
