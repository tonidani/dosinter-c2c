
// Register Service Worker
if ('serviceWorker' in navigator) {
    navigator.serviceWorker
    .register('/service-worker.js')
    .then(function(registration) {

        return registration;
    })
    .catch(function(err) {

    });
}




let deferredPrompt;
const addBtn = document.querySelector('.add-button');
addBtn.style.display = 'none';



window.addEventListener('beforeinstallprompt', (e) => {
  e.preventDefault();
  deferredPrompt = e;
  addBtn.style.display = 'block';
  addBtn.addEventListener('click', (e) => {
    addBtn.style.display = 'none';
    deferredPrompt.prompt();
    deferredPrompt.userChoice.then((choiceResult) => {
        if (choiceResult.outcome === 'accepted') {





        } else {



        }
        deferredPrompt = null;
      });
  });
});




window.addEventListener('online', function(e) {

}, false);
