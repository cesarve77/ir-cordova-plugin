      var ir = {

        transmit: function(frequency, signal,successCallback, errorCallback){
          cordova.exec(successCallback,
              errorCallback,
              "Ir",
              "transmit",
              [{
                "frequency":frequency,
                  "signal":signal
              }]
          );

        }
      }

      module.exports = ir;
